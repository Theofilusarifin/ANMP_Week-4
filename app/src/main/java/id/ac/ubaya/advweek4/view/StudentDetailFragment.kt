package id.ac.ubaya.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.advweek4.R
import id.ac.ubaya.advweek4.databinding.FragmentStudentDetailBinding
import id.ac.ubaya.advweek4.viewmodel.DetailViewModel

class StudentDetailFragment : Fragment(),
    ButtonUpdateClickListener {

    private lateinit var viewDetail: DetailViewModel
    private lateinit var dataBinding: FragmentStudentDetailBinding

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

        if(arguments != null) {
            val studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId

            viewDetail = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewDetail.fetch()

            observeDetailViewModel()
        }
    }

    fun observeDetailViewModel() {
        viewDetail.studentLD.observe(viewLifecycleOwner, Observer {
            dataBinding.student = it

//            txtIdDetail.setText(it.id)
//            txtNameDetail.setText(it.name)
//            txtBod.setText(it.bod)
//            txtPhone.setText(it.phone)
//
//            imageView2.loadImage(it.photoUrl.toString(), progressBar2)
        })
    }

    override fun onButtonUpdateClick(v: View) {
        Toast.makeText(v.context, "Update Success", Toast.LENGTH_SHORT).show()
    }
}