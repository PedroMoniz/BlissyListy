package com.pedromoniz.blissylisty.view.questionsListFeature.pagedListVersion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pedromoniz.blissylisty.databinding.QuestionPagedTemplateRowBinding
import com.pedromoniz.blissylisty.domain.entities.QuestionEntity
import kotlinx.android.synthetic.main.question_paged_template_row.view.*


class QuestionsPagedListAdapter(
    private val viewModel: QuestionsPagedListViewModel
) : PagedListAdapter<QuestionEntity, QuestionsPagedListAdapter.BaseQuestionViewHolder<*>>(
    diffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseQuestionViewHolder<*> =
        when (viewType) {
            TYPE_SIMPLE -> {
                val binding = QuestionPagedTemplateRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SimpleQuestionViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }

    override fun onBindViewHolder(holder: BaseQuestionViewHolder<*>, position: Int) {
        val element =  getItem(position)
        when (holder) {
            is SimpleQuestionViewHolder -> element?.let { holder.bind(it) }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE_SIMPLE
    }

    abstract class BaseQuestionViewHolder<T>(root: View) : RecyclerView.ViewHolder(root) {
        abstract fun bind(item: T)
    }

    inner class SimpleQuestionViewHolder(val binding: QuestionPagedTemplateRowBinding) :
        BaseQuestionViewHolder<QuestionEntity>(binding.root) {

        override fun bind(item: QuestionEntity) {

            Glide.with(itemView.context)
                .load(item.thumb_url)
                .centerCrop()
                .thumbnail()
                .into(itemView.questionPagedTemplateImageView)


            binding.viewmodel = viewModel
            binding.question = item
            binding.executePendingBindings()
        }
    }


    companion object {
        private const val TYPE_SIMPLE = 0

        private val diffCallback = object : DiffUtil.ItemCallback<QuestionEntity>() {

            override fun areItemsTheSame(oldItem: QuestionEntity, newItem: QuestionEntity) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: QuestionEntity, newItem: QuestionEntity) = oldItem == newItem
        }
    }
}