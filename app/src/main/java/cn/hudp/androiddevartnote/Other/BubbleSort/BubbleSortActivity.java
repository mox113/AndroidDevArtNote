package cn.hudp.androiddevartnote.Other.BubbleSort;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cn.hudp.androiddevartnote.R;

/**
 * 冒泡排序
 */
public class BubbleSortActivity extends AppCompatActivity {
    private int[] nums = {1, 3, 2, 5, 6, -3, 9};//冒泡排序
    private int[] nums2 = {1, 3, 2, 5, 6, -3, 9};//选择排序
    private TextView tv, tvSort;
    private StringBuffer stringBuffer, sortBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_sort);
        tv = (TextView) findViewById(R.id.tv);
        tvSort = (TextView) findViewById(R.id.tv_sort);
        stringBuffer = new StringBuffer();
        sortBuffer = new StringBuffer();

        initSort();
        initSeelctSort();
    }

    private void initSeelctSort() {

    }

    private void initSort() {
        stringBuffer.append("冒泡 排序前：");
        for (int num : nums) {
            stringBuffer.append(num);
            stringBuffer.append(",");
        }
        tv.setText(stringBuffer.toString());

        int temp = 0;
        for (int i = 0; i < nums.length - 1; i++) {//控制比较的轮数
            for (int j = 0; j < nums.length - 1 - i; j++) {//控制每轮比较的次数
                if (nums[j] > nums[j + 1]) {
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        stringBuffer.append("\n冒泡 排序后：");
        for (int num : nums) {
            stringBuffer.append(num);
            stringBuffer.append(",");
        }
        tv.setText(stringBuffer.toString());
    }
}
