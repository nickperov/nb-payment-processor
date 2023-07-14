package com.nickperov.labs.nb.payment_processor

import io.smallrye.common.annotation.RunOnVirtualThread
import jakarta.inject.Inject
import jakarta.ws.rs.*
import org.jboss.logging.Logger
import java.math.BigDecimal
import java.util.*

@Path("payment")
class PaymentController {

    @Inject
    lateinit var log: Logger

    private var successRate: Int = 100

    private var delay: Long = 0L

    @POST
    @RunOnVirtualThread
    @Path("/execute")
    fun processPayment(payment: PaymentDTO): Boolean {
        log.info("Processing payment - " + payment.reference)
        // Simulate processing delay
        Thread.sleep(delay)

        // Simulate random success
        if (successRate < 100) {
            return Random().nextInt(100) < successRate
        } else {
            return true
        }
    }

    @GET
    @Path("/delay")
    fun getDelay(): Long {
        return delay
    }

    @PUT
    @Path("/delay/{delay}")
    fun setDelay(@PathParam("delay") delay: Long) {
        if (delay < 0 || delay > 1000_000) {
            throw BadRequestException("Invalid delay: $delay")
        }
        this.delay = delay
    }

    @GET
    @Path("/success-rate")
    fun getSuccessRate(): Int {
        return successRate
    }

    @PUT
    @Path("/success-rate/{rate}")
    fun setSuccessRate(@PathParam("rate") rate: Int) {
        if (rate < 0 || rate > 100) {
            throw BadRequestException("Invalid rate: $rate")
        }
        this.successRate = rate
    }

}

data class PaymentDTO(val reference: UUID, val debitAcc: String, val creditAcc: String, val amount: BigDecimal)