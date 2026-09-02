package com.bitmesra_ml.yolov8pestdetection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.GridLayoutManager


data class Pest(
    val name: String,
    val imageResId: Int,
    val description: String,
    var isExpanded: Boolean = false
)

class PestListFragment : Fragment() {

    private lateinit var pestRecyclerView: RecyclerView
    private lateinit var pestAdapter: PestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pest_list, container, false)
        pestRecyclerView = view.findViewById(R.id.pestRecyclerView)

        val pestList = listOf(
            Pest("Rice Leaf Roller", R.drawable.leaf_roller, "Aphids are tiny insects that suck sap from plants."),
            Pest("Rice Leaf Caterpillar", R.drawable.rice_leaf_catterpillar, "White, cottony pests that weaken plants."),
            Pest("Asiatic Rice Borer", R.drawable.asiatic_borer, "Chews leaves and destroys foliage."),
            // Add remaining pests here...
        )

        pestAdapter = PestAdapter(pestList)
        pestRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        pestRecyclerView.adapter = pestAdapter

        return view
    }
}
