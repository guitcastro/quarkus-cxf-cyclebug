package com.simpleps.app.cxfCycle

import com.fasterxml.jackson.databind.ObjectMapper
import io.quarkus.runtime.annotations.RegisterForReflection
import org.eclipse.microprofile.openapi.OASFactory
import org.eclipse.microprofile.openapi.OASFilter
import org.eclipse.microprofile.openapi.models.examples.Example
import org.eclipse.microprofile.openapi.models.parameters.Parameter
import org.eclipse.microprofile.openapi.models.parameters.RequestBody
import org.eclipse.microprofile.openapi.models.responses.APIResponse

@RegisterForReflection
class PojoExampleOASFilter : OASFilter {

    override fun filterAPIResponse(apiResponse: APIResponse): APIResponse {
        val mediaTypes = apiResponse.content?.mediaTypes?.mapValues {
            it.value.examples = transformExamples(it.value?.examples)
            it.value
        }
        apiResponse.content?.mediaTypes = mediaTypes
        return apiResponse
    }

    override fun filterRequestBody(requestBody: RequestBody): RequestBody {
        val mediaTypes = requestBody.content?.mediaTypes?.mapValues {
            it.value.examples = transformExamples(it.value?.examples)
            it.value
        }
        requestBody.content?.mediaTypes = mediaTypes
        return super.filterRequestBody(requestBody)
    }
    override fun filterParameter(parameter: Parameter): Parameter {
        parameter.examples = transformExamples(parameter.examples)
        return parameter
    }

    private fun transformExamples(examples: Map<String, Example>?) = examples?.mapValues { example ->
        if (!example.value.externalValue.isNullOrEmpty()) {
            val filePath = "/META-INF/resources/examples/${example.value.externalValue}"
            val value = this::class.java.getResource(filePath)?.readText()
            val jsonValue = ObjectMapper().readValue(value, Map::class.java)
            OASFactory.createExample()
                .ref(example.value.ref)
                .summary(example.value.summary)
                .description(example.value.description)
                .value(jsonValue)
        } else {
            example.value
        }
    }
}
