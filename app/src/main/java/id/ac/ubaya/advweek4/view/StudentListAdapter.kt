package id.ac.ubaya.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.advweek4.R
import id.ac.ubaya.advweek4.databinding.StudentListItemBinding
import id.ac.ubaya.advweek4.model.Student
import id.ac.ubaya.advweek4.util.loadImage
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter(val studentList:ArrayList<Student>): RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonDetailClickListener {
    class StudentViewHolder(var view: StudentListItemBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        val view = DataBindingUtil.inflate<StudentListItemBinding>(inflater,
            R.layout.student_list_item, parent, false)

        return  StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.student = studentList[position]
        holder.view.listener = this
    //        holder.view.findViewById<TextView>(R.id.txtId).text = studentList[position].id
//        holder.view.findViewById<TextView>(R.id.txtStudentName).text = studentList[position].name
//        holder.view.findViewById<ImageView>(R.id.imgStudentProfile).loadImage(studentList[position].photoUrl, holder.view.progressBar)


//        holder.view.findViewById<Button>(R.id.btnDetail).setOnClickListener{
//            val action = StudentListFragmentDirections.actionStudentDetail(studentList[position].id.toString())
//            Navigation.findNavController(it).navigate(action)
//        }

    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }

    fun updateStudentList(newStudentList:ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

}