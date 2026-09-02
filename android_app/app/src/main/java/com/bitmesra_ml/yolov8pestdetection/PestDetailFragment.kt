package com.bitmesra_ml.yolov8pestdetection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class PestDetailFragment : Fragment() {

    companion object {
        private const val ARG_NAME = "name"
        private const val ARG_IMAGE = "image"
        private const val ARG_DESC = "desc"

        fun newInstance(pest: Pest): PestDetailFragment {
            val fragment = PestDetailFragment()
            val args = Bundle()
            args.putString(ARG_NAME, pest.name)
            args.putInt(ARG_IMAGE, pest.imageResId)
            args.putString(ARG_DESC, pest.description)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pest_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val image = view.findViewById<ImageView>(R.id.detailImage)
        val name = view.findViewById<TextView>(R.id.detailName)
        val desc = view.findViewById<TextView>(R.id.detailDescription)
        val back = view.findViewById<ImageView>(R.id.backButton)

        image.setImageResource(arguments?.getInt(ARG_IMAGE) ?: 0)
        name.text = arguments?.getString(ARG_NAME)
        desc.text = arguments?.getString(ARG_DESC)

        back.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}
