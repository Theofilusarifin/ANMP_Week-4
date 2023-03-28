package id.ac.ubaya.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.advweek4.model.Student

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()

    fun fetch() {
        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
        studentLD.value = student1
    }

}