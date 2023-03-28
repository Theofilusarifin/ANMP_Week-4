package id.ac.ubaya.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import id.ac.ubaya.advweek4.R
import id.ac.ubaya.advweek4.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_student_list.*

class StudentListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val studentListAdapter = StudentListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        val recView = view.findViewById<RecyclerView>(R.id.recView)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = studentListAdapter

        observeViewModel()

        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        swipe.setOnRefreshListener {
            recView.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.studentsLD.observe(viewLifecycleOwner){
            studentListAdapter.updateStudentList(it)
        }
        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner){
            val txtError = view?.findViewById<TextView>(R.id.txtError)

            txtError?.visibility = if(it) View.VISIBLE else View.GONE

            viewModel.loadingLD.observe(viewLifecycleOwner){
                val recView = view?.findViewById<RecyclerView>(R.id.recView)
                val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoad)

                if(it){ //sedang loading
                    recView?.visibility = View.GONE
                    progressLoad?.visibility = View.VISIBLE
                    txtError?.visibility = View.VISIBLE
                }else{
                    recView?.visibility = View.VISIBLE
                    progressLoad?.visibility = View.GONE
                    txtError?.visibility = View.GONE

                }
            }

        }
    }

}