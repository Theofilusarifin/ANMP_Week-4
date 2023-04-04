package id.ac.ubaya.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import id.ac.ubaya.advweek4.model.Student

//class DetailViewModel: ViewModel() {
class DetailViewModel(application: Application): AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()

//    fun fetch() {
//        val student1 = Student("16055","Nonie","1998/03/28","5718444778", "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
//        studentLD.value = student1
//    }

    private var TAG = "volleyTag"
    private var queue: RequestQueue?= null

    fun fetch(studentId: String) {
        queue = Volley.newRequestQueue(getApplication())
        var url = "http://adv.jitusolution.com/student.php?id=$studentId"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val result = Gson().fromJson<Student>(response, Student::class.java)
                studentLD.value = result
                Log.d("showvolley", response.toString())
            },
            {
                Log.d("showvolley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}