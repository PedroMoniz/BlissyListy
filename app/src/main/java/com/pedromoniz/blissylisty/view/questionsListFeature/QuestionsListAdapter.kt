package com.pedromoniz.blissylisty.view.questionsListFeature

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pedromoniz.blissylisty.databinding.QuestionTemplateRowBinding
import com.pedromoniz.blissylisty.domain.entities.QuestionEntity
import kotlinx.android.synthetic.main.question_template_row.view.*


class QuestionsListAdapter(
    private val viewModel: QuestionsListViewModel,
    private var data: List<QuestionEntity> = emptyList()
) : RecyclerView.Adapter<QuestionsListAdapter.BaseQuestionViewHolder<*>>() {


    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseQuestionViewHolder<*> =
        when (viewType) {
            TYPE_SIMPLE -> {
                val binding = QuestionTemplateRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SimpleQuestionViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }

    override fun onBindViewHolder(holder: BaseQuestionViewHolder<*>, position: Int) {
        val element = data[position]
        when (holder) {
            is SimpleQuestionViewHolder -> holder.bind(element)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE_SIMPLE
    }

    fun setData(newData: List<QuestionEntity>) {
        data = newData
        notifyDataSetChanged()
    }

    abstract class BaseQuestionViewHolder<T>(root: View) : RecyclerView.ViewHolder(root) {
        abstract fun bind(item: T)
    }

    inner class SimpleQuestionViewHolder(val binding: QuestionTemplateRowBinding) :
        BaseQuestionViewHolder<QuestionEntity>(binding.root) {

        override fun bind(item: QuestionEntity) {

            Glide.with(itemView.context)
                .load(item.thumb_url)
                .centerCrop()
                .thumbnail()
                .into(itemView.questionTemplateImageView)


            binding.viewmodel = viewModel
            binding.question = item
            binding.executePendingBindings()
        }
    }


    companion object {
        private const val TYPE_SIMPLE = 0
    }
}