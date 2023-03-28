package id.ac.ubaya.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.advweek4.R
import id.ac.ubaya.advweek4.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_student_list.*

class StudentListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val studentListAdapter  = StudentListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = studentListAdapter

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer {
            studentListAdapter.updateStudentList(it)
        })

        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer {
            txtError.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if (it) {
                progressLoad.visibility = View.VISIBLE
                recView.visibility = View.GONE
            } else {
                progressLoad.visibility = View.GONE
                recView.visibility = View.VISIBLE
            }
        })
    }
}