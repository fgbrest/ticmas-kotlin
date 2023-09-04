package com.curso.android.app.practica.fgbapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.curso.android.app.practica.fgbapp.view.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.Rule

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class MainViewModelUnitTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private lateinit var viewModel: MainViewModel
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = MainViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun mainViewModel_CheckInitialValue() = runTest {
        assertEquals(null, viewModel.stringComparison.value)
        assertNull(viewModel.errorMsg.value)
    }

    @Test
    fun mainViewModel_CompareEqualStrings() = runTest {
        val str1Val = "qwerty"
        val str2Val = "qwerty"
        launch {
            viewModel.compareStrings(str1Val, str2Val)
        }
        advanceUntilIdle()
        val result = viewModel.stringComparison.value?.comparisonResult
        assertTrue(result!!)
        val errorMsg = viewModel.errorMsg.value
        assertNull(errorMsg)
    }

    @Test
    fun mainViewModel_CompareDifferentStrings() = runTest {
        val str1Val = "qwerty"
        val str2Val = "qwertY"
        launch {
            viewModel.compareStrings(str1Val, str2Val)
        }
        advanceUntilIdle()
        val result = viewModel.stringComparison.value?.comparisonResult
        assertFalse(result!!)
        val errorMsg = viewModel.errorMsg.value
        assertNull(errorMsg)
    }

    @Test(expected = Exception::class)
    fun mainViewModel_CompareEmptyString() = runTest {
        val str1Val = "qwerty"
        val str2Val = ""
        launch {
            viewModel.compareStrings(str1Val, str2Val)
        }
        advanceUntilIdle()
        assertNotNull(viewModel.errorMsg.value)
    }
}
