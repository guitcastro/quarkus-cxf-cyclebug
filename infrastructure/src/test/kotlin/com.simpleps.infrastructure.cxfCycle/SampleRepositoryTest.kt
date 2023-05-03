package com.simpleps.infrastructure.cxfCycle

import com.simpleps.domain.cxfCycle.Sample
import com.simpleps.domain.cxfCycle.SampleRepository
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class SampleRepositoryTest {

    private val sampleRepository: SampleRepository = SampleRepositoryImpl()

    @Test
    fun test() {
        val sample = sampleRepository.createSample(Sample())
        assertNotNull(sample.id)
    }
}
