package kpk.dev.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class LiveDataResourceTest {
    private lateinit var liveData: MutableLiveData<Resource<Int>>
    private val errorMsg = "An error occurred"
    private val testData = 1

    @Rule
    @JvmField
    val instantExecutionRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        liveData = MutableLiveData()
    }

    @Test
    fun `live data - loading to success`() {
        liveData.setLoading()

        liveData.setSuccess(testData)

        assertEquals(Resource(ResourceState.SUCCESS, testData, null), liveData.value)
    }

    @Test
    fun `live data - loading to failure`() {
        liveData.setLoading()

        liveData.setFailure(errorMsg)

        assertEquals(Resource(ResourceState.FAILURE, null, message = errorMsg), liveData.value)
    }

    @Test
    fun `live data - loading to success to loading to failure`() {
        liveData.setLoading()

        liveData.setSuccess(testData)

        liveData.setLoading()

        liveData.setFailure(errorMsg)

        assertEquals(Resource(ResourceState.FAILURE, testData, message = errorMsg), liveData.value)
    }

    @Test
    fun `live data - loading to failure to loading to success`() {
        liveData.setLoading()

        liveData.setFailure(errorMsg)

        liveData.setLoading()

        liveData.setSuccess(testData)

        assertEquals(Resource(ResourceState.SUCCESS, testData, null), liveData.value)
    }
}