package com.cn.luo.demo.ui;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.blankj.utilcode.util.ToastUtils;
import com.cn.luo.demo.R;
import com.cn.luo.demo.adapter.MyItemClickListener;
import com.cn.luo.demo.adapter.VLayoutAdapter;
import com.cn.luo.demo.base.BaseFragment;
import com.cn.luo.demo.databinding.FragmentVLayoutBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * AUTHOR:       Luo
 * VERSION:      V1.0
 * DESCRIPTION:  description
 * CREATE TIME:  2017/7/28 9:34
 * NOTE:
 */
public class VLayoutFragment extends BaseFragment implements MyItemClickListener {

    private FragmentVLayoutBinding binding;
    private VLayoutAdapter Adapter_linearLayout, Adapter_GridLayout, Adapter_FixLayout, Adapter_ScrollFixLayout,
            Adapter_FloatLayout, Adapter_ColumnLayout, Adapter_SingleLayout, Adapter_onePlusNLayout,
            Adapter_StickyLayout, Adapter_StaggeredGridLayout;
    private ArrayList<HashMap<String, Object>> listItem;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_v_layout;
    }

    @Override
    protected void initUI() {
        /**
         * 步骤1：创建RecyclerView & VirtualLayoutManager 对象并进行绑定
         * */
        binding = DataBindingUtil.bind(rootView);
        // 创建RecyclerView对象
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(getContext());
        // 创建VirtualLayoutManager对象
        // 同时内部会创建一个LayoutHelperFinder对象，用来后续的LayoutHelper查找
        binding.recyclerView.setLayoutManager(layoutManager);
        // 将VirtualLayoutManager绑定到recyclerView
        /**
         * 步骤2：设置组件复用回收池
         * */
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        binding.recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
        /**
         * 步骤3:设置需要存放的数据
         * */
        listItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 100; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", "第" + i + "行");
            map.put("ItemImage", R.mipmap.ic_launcher);
            listItem.add(map);
        }
        /**
         * 步骤4:根据数据列表,创建对应的LayoutHelper
         * */
        // 为了展示效果,此处将上面介绍的所有布局都显示出来
        /**
         设置线性布局
         */
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        // 创建对应的LayoutHelper对象
        // 公共属性
        linearLayoutHelper.setItemCount(4);// 设置布局里Item个数
        linearLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        linearLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        linearLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        linearLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        // linearLayoutHelper特有属性
        linearLayoutHelper.setDividerHeight(10);// 设置间隔高度
        // 设置的间隔会与RecyclerView的addItemDecoration（）添加的间隔叠加.
        linearLayoutHelper.setMarginBottom(100);// 设置布局底部与下个布局的间隔
        // 创建自定义的Adapter对象 & 绑定数据 & 绑定对应的LayoutHelper进行布局绘制
        Adapter_linearLayout = new VLayoutAdapter(getContext(), linearLayoutHelper, 30, listItem) {
            // 参数2:绑定绑定对应的LayoutHelper
            // 参数3:传入该布局需要显示的数据个数
            // 参数4:传入需要绑定的数据
            // 通过重写onBindViewHolder()设置更丰富的布局效果
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                // 为了展示效果,将布局的第一个数据设置为linearLayout
                if (position == 0) {
                    holder.Text.setText("Linear");
                }
                //为了展示效果,将布局里不同位置的Item进行背景颜色设置
                if (position < 2) {
                    holder.itemView.setBackgroundColor(0x66cc0000 + (position - 6) * 128);
                } else if (position % 2 == 0) {
                    holder.itemView.setBackgroundColor(0xaa22ff22);
                } else {
                    holder.itemView.setBackgroundColor(0xccff22ff);
                }
            }
        };
        Adapter_linearLayout.setOnItemClickListener(this);
        // 设置每个Item的点击事件 ....
        /**
         设置吸边布局
         */
        StickyLayoutHelper stickyLayoutHelper = new StickyLayoutHelper();

        // 公共属性
        stickyLayoutHelper.setItemCount(3);// 设置布局里Item个数
        stickyLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        stickyLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        stickyLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        stickyLayoutHelper.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比

        // 特有属性
        stickyLayoutHelper.setStickyStart(true);
        // true = 组件吸在顶部
        // false = 组件吸在底部

        stickyLayoutHelper.setOffset(100);// 设置吸边位置的偏移量

        Adapter_StickyLayout = new VLayoutAdapter(context, stickyLayoutHelper, 1, listItem) {
            // 设置需要展示的数据总数,此处设置是1
            // 为了展示效果,通过重写onBindViewHolder()将布局的第一个数据设置为Stick
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 0) {
                    holder.Text.setText("Stick");
                }
            }
        };
        /**
         设置可选固定布局
         */
        ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(ScrollFixLayoutHelper.TOP_RIGHT, 0, 0);
        // 参数说明:
        // 参数1:设置吸边时的基准位置(alignType) - 有四个取值:TOP_LEFT(默认), TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
        // 参数2:基准位置的偏移量x
        // 参数3:基准位置的偏移量y
        // 公共属性 scrollFixLayoutHelper.setItemCount(1);
        // 设置布局里Item个数
        // 从设置Item数目的源码可以看出，一个FixLayoutHelper只能设置一个
        //        @Override
        //        public void setItemCount(int itemCount) {
        //            if (itemCount > 0) {
        //                super.setItemCount(1);
        //            } else {
        //                super.setItemCount(0);
        //            }
        //        }
        scrollFixLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        scrollFixLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        scrollFixLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        scrollFixLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        // fixLayoutHelper特有属性
        scrollFixLayoutHelper.setAlignType(FixLayoutHelper.TOP_LEFT);// 设置吸边时的基准位置(alignType)
        scrollFixLayoutHelper.setX(30);// 设置基准位置的横向偏移量X
        scrollFixLayoutHelper.setY(50);// 设置基准位置的纵向偏移量Y
        scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_ENTER);// 设置Item的显示模式
        Adapter_ScrollFixLayout = new VLayoutAdapter(context, scrollFixLayoutHelper, 4, listItem);

        /**
         设置Grid布局
         */
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);// 在构造函数设置每行的网格个数
        // 公共属性
        gridLayoutHelper.setItemCount(6);// 设置布局里Item个数
        gridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        gridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        gridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        gridLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        // gridLayoutHelper特有属性（下面会详细说明）
        gridLayoutHelper.setWeights(new float[]{40, 30, 30});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        gridLayoutHelper.setSpanCount(3);// 设置每行多少个网格
        // 通过自定义SpanSizeLookup来控制某个Item的占网格个数
//        gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                if (position > 7) {
//                    return 3;
//                    // 第7个位置后,每个Item占3个网格
//                } else {
//                    return 2;
//                    // 第7个位置前,每个Item占2个网格
//                }
//            }
//        });
        Adapter_GridLayout = new VLayoutAdapter(context, gridLayoutHelper, 6, listItem);
        /**
         设置固定布局
         */
        FixLayoutHelper fixLayoutHelper = new FixLayoutHelper(FixLayoutHelper.TOP_LEFT, 40, 100);
        // 参数说明:
        // 参数1:设置吸边时的基准位置(alignType) - 有四个取值:TOP_LEFT(默认), TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
        // 参数2:基准位置的偏移量x
        // 参数3:基准位置的偏移量y
        // 公共属性
        fixLayoutHelper.setItemCount(1);
        // 设置布局里Item个数
        // 从设置Item数目的源码可以看出，一个FixLayoutHelper只能设置一个
        //        @Override
        //        public void setItemCount(int itemCount) {
        //            if (itemCount > 0) {
        //                super.setItemCount(1);
        //            } else {
        //                super.setItemCount(0);
        //            }
        //        }
        fixLayoutHelper.setPadding(20, 20, 20, 20);
        // 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        fixLayoutHelper.setMargin(20, 20, 20, 20);
        // 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        fixLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        fixLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        // fixLayoutHelper特有属性
        fixLayoutHelper.setAlignType(FixLayoutHelper.TOP_LEFT);// 设置吸边时的基准位置(alignType)
        fixLayoutHelper.setX(30);// 设置基准位置的横向偏移量X
        fixLayoutHelper.setY(50);// 设置基准位置的纵向偏移量Y
        Adapter_FixLayout = new VLayoutAdapter(context, fixLayoutHelper, 10, listItem);
        /**
         设置浮动布局
         */
        FloatLayoutHelper floatLayoutHelper = new FloatLayoutHelper(); // 创建FloatLayoutHelper对象
        // 公共属性
        floatLayoutHelper.setItemCount(1);// 设置布局里Item个数
        // 从设置Item数目的源码可以看出，一个FixLayoutHelper只能设置一个
        //        @Override
        //        public void setItemCount(int itemCount) {
        //            if (itemCount > 0) {
        //                super.setItemCount(1);
        //            } else {
        //                super.setItemCount(0);
        //            }
        //        }
        floatLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        floatLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        floatLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        floatLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        // floatLayoutHelper特有属性
        floatLayoutHelper.setDefaultLocation(100, 100);// 设置布局里Item的初始位置
        Adapter_FloatLayout = new VLayoutAdapter(context, floatLayoutHelper, 1, listItem);
        /**
         设置栏格布局
         */
        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
        // 创建对象
        // 公共属性
        columnLayoutHelper.setItemCount(3);// 设置布局里Item个数
        columnLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        columnLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        columnLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        columnLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        // columnLayoutHelper特有属性
        columnLayoutHelper.setWeights(new float[]{30, 40, 30});// 设置该行每个Item占该行总宽度的比例
        // 同上面Weigths属性讲解
        Adapter_ColumnLayout = new VLayoutAdapter(context, columnLayoutHelper, 10, listItem);
        /**
         设置通栏布局
         */
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        // 公共属性
        singleLayoutHelper.setItemCount(3);// 设置布局里Item个数
        singleLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        singleLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        singleLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        singleLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        Adapter_SingleLayout = new VLayoutAdapter(context, singleLayoutHelper, 10, listItem);
        /**
         设置1拖N布局
         */
        OnePlusNLayoutHelper onePlusNLayoutHelper = new OnePlusNLayoutHelper(5);
        // 在构造函数里传入显示的Item数
        // 最多是1拖4,即5个
        // 公共属性
        onePlusNLayoutHelper.setItemCount(3);
        // 设置布局里Item个数
        onePlusNLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        onePlusNLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        onePlusNLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        onePlusNLayoutHelper.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比
        Adapter_onePlusNLayout = new VLayoutAdapter(context, onePlusNLayoutHelper, 5, listItem);
        /**
         设置瀑布流布局
         */
        StaggeredGridLayoutHelper staggeredGridLayoutHelper = new StaggeredGridLayoutHelper();
        // 创建对象
        // 公有属性
        staggeredGridLayoutHelper.setItemCount(20);// 设置布局里Item个数
        staggeredGridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        staggeredGridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        staggeredGridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        staggeredGridLayoutHelper.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比
        // 特有属性
        staggeredGridLayoutHelper.setLane(3);// 设置控制瀑布流每行的Item数
        staggeredGridLayoutHelper.setHGap(20);// 设置子元素之间的水平间距
        staggeredGridLayoutHelper.setVGap(15);// 设置子元素之间的垂直间距
        Adapter_StaggeredGridLayout = new VLayoutAdapter(context, staggeredGridLayoutHelper, 10, listItem);
        // 还有其他布局，由于代码量就较多就不贴出来了。
        /**
         *步骤5:
         将生成的LayoutHelper 交给Adapter，
         并绑定到RecyclerView 对象
         **/
        // 1. 设置Adapter列表（同时也是设置LayoutHelper列表）
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        // 2. 将上述创建的Adapter对象放入到DelegateAdapter.Adapter列表里
        adapters.add(Adapter_linearLayout);
        adapters.add(Adapter_StickyLayout);
        adapters.add(Adapter_ScrollFixLayout);
        adapters.add(Adapter_GridLayout);
        adapters.add(Adapter_FixLayout);
        adapters.add(Adapter_FloatLayout);
        adapters.add(Adapter_ColumnLayout);
        adapters.add(Adapter_SingleLayout);
        adapters.add(Adapter_onePlusNLayout);
        adapters.add(Adapter_StaggeredGridLayout);
        // 3. 创建DelegateAdapter对象 & 将layoutManager绑定到DelegateAdapter
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);
        // 4. 将DelegateAdapter.Adapter列表绑定到DelegateAdapter
        delegateAdapter.setAdapters(adapters);
        // 5. 将delegateAdapter绑定到recyclerView
        binding.recyclerView.setAdapter(delegateAdapter);
        /**
         *步骤6:
         Item之间的间隔
         **/
        binding.recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView
                    parent, RecyclerView.State state) {
                outRect.set(5, 5, 5, 5);
            }
        });
    }

    /**
     * 步骤7:实现Item点击事件
     **/
    // 点击事件的回调函数
    @Override
    public void onItemClick(View view, int postion) {
        System.out.println("点击了第" + postion + "行");
        ToastUtils.showShort((String) listItem.get(postion).get("ItemTitle"));
    }

    @Override
    public void onClick(View view) {

    }
}
