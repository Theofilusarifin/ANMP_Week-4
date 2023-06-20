package id.ac.ubaya.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.advweek4.R
import id.ac.ubaya.advweek4.databinding.FragmentStudentDetailBinding
import id.ac.ubaya.advweek4.util.loadImage
import id.ac.ubaya.advweek4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), ButtonUpdateClickListener  {
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: FragmentStudentDetailBinding

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_student_detail, container, false)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(
            inflater, R.layout.fragment_student_detail, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
//
//        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
//        viewModel.fetch(studentId)
//
//        observeViewModel(view)

        if(arguments != null) {
            val studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId

            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(studentId)

            observeViewModel()

            dataBinding.listener = this
        }
    }

    fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            dataBinding.student = it
        })
    }

    override fun onButtonUpdateClick(v: View) {
        Toast.makeText(v.context, "Update Success", Toast.LENGTH_SHORT).show()
    }

//    fun observeViewModel(view: View){
//        viewModel.studentLD.observe(viewLifecycleOwner, Observer{
//            txtEditId.setText(it.id)
//            txtEditName.setText(it.name)
//            txtEditBirth.setText(it.bod)
//            txtEditPhone.setText(it.phone)
//            imageView.loadImage(it.photoUrl, progressBarDetail)
//
//            var student = it
//            btnNotif.setOnClickListener {
//                Log.e("Button", "Masuk Notif Button")
//                Observable.timer(1, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "one second")
//                        MainActivity.showNotification(student.name.toString(),
//                            "A new notification created",
//                            R.drawable.ic_baseline_check_circle_24)
//                    }
//            }
//
//
//        })
//    }
}