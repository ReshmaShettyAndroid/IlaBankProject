package com.example.ilabankproject.views.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.ilabankproject.R
import com.example.ilabankproject.databinding.FragmentSliderBinding
import com.example.ilabankproject.model.ImageDataDto
import com.example.ilabankproject.viewModel.SlideViewModel
import com.example.ilabankproject.views.adapter.LableRecyclerAdapter
import com.example.ilabankproject.views.adapter.ViewPagerAdapter

class SlideFragment : Fragment() {
    private lateinit var mBinding: FragmentSliderBinding
    private lateinit var mViewModel: SlideViewModel
    private lateinit var mViewPagerAdapter: ViewPagerAdapter;
    private var mdotscount = 0
    private var dots: ArrayList<ImageView> = ArrayList();
    private var labelList: MutableList<ImageDataDto>? = null;
    private lateinit var labelAdapter: LableRecyclerAdapter;
    private var selectedLabelList: MutableList<String>? = null;
    private var previousSelectedTab = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = FragmentSliderBinding.inflate(inflater, container, false)
        objectInitialization()
        viewInitialization()
        return mBinding.root
    }

    fun objectInitialization() {
        mViewModel = ViewModelProvider(this).get(SlideViewModel::class.java)
    }

    fun viewInitialization() {
        liveDataObserver()
        viewPageAdatperSetting(requireContext());
        recyclerViewSetting();
        searchData();
        createSliderDotsViews()
    }

    fun viewPageAdatperSetting(context: Context) {
        labelList = mViewModel.prepareImageList()!!.value
        mViewPagerAdapter = ViewPagerAdapter(labelList)
        mBinding.viewpager.setAdapter(mViewPagerAdapter)

        mBinding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                dots.get(previousSelectedTab).setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.nonactive_dots)
                )
                dots.get(position).setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.activedot)
                )
                previousSelectedTab = position
                selectedLabelList = labelList?.get(position)?.labels;
                setadapter();
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }

    fun liveDataObserver() {
        mViewModel.imageModelList.observe(requireActivity()) {
            mViewPagerAdapter.notifyDataSetChanged()
        }
    }

    fun createSliderDotsViews() {
        mdotscount = mViewPagerAdapter.getItemCount();
        for (i in 0 until mdotscount) {
            dots.add(ImageView(requireContext()))
            dots.get(i).setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.nonactive_dots
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            mBinding.linrSliderDots.addView(dots.get(i), params)
        }

        dots.get(0).setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.activedot
            )
        )
    }

    fun recyclerViewSetting() {
        mBinding.recylcerLabels.layoutManager = LinearLayoutManager(requireContext())
        selectedLabelList = labelList?.get(0)?.labels;
        setadapter();
        mBinding.recylcerLabels.setNestedScrollingEnabled(false);
    }

    fun setadapter() {
        mBinding.searchTxt.setQuery("", false);
        mBinding.searchTxt.clearFocus();
        labelAdapter = LableRecyclerAdapter(selectedLabelList)
        mBinding.recylcerLabels.adapter = labelAdapter
    }

    fun searchData() {
        mBinding.searchTxt.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                labelAdapter?.filter(selectedLabelList, newText)
                return false
            }
        })
    }
}