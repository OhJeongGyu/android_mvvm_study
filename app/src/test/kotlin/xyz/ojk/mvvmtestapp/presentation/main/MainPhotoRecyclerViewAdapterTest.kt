package xyz.ojk.mvvmtestapp.presentation.main

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import xyz.ojk.mvvmtestapp.data.entity.Photo
import xyz.ojk.mvvmtestapp.di.NetworkModule
import xyz.ojk.mvvmtestapp.presentation.main.adapter.MainPhotoRecyclerAdapter
import javax.inject.Inject

/**
 * Created by jeonggyu on 2018. 3. 25..
 */
@RunWith(RobolectricTestRunner::class)
class MainPhotoRecyclerViewAdapterTest {

    val application = RuntimeEnvironment.application

    lateinit var photoRecyclerViewAdapter: MainPhotoRecyclerAdapter
    @Inject lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        DaggerMainComponent.builder()
                .networkModule(NetworkModule())
                .build()
                .inject(this)

        photoRecyclerViewAdapter = MainPhotoRecyclerAdapter(application, viewModel)
    }

    @Test
    fun makeThumnailUrlTest() {
        val photo = Photo(id="testId", owner = "testOwner", server = "HelloServer", farm = 4, title="testTitle", secret = "testSecret")

        assert(photoRecyclerViewAdapter.makeThumnailUrl(photo) == "http://farm4.staticflickr.com/HelloServer/testId_testSeceret_m.jpg")

    }

}