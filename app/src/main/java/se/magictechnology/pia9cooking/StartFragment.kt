package se.magictechnology.pia9cooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StartFragment : Fragment() {

    var hightlightsadapter = HighlightsAdapter()
    var categoriesadapter = CategoriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var highlightsrv = view.findViewById<RecyclerView>(R.id.startHighlightsRV)

        highlightsrv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        highlightsrv.adapter = hightlightsadapter

        var categoriesrv = view.findViewById<RecyclerView>(R.id.startCategoriesRV)

        categoriesrv.layoutManager = LinearLayoutManager(context)
        categoriesrv.adapter = categoriesadapter

    }
}