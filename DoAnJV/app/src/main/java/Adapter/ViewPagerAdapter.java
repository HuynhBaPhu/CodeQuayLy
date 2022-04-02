package Adapter;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.doanjv.Fragment_ThongTin;
import com.example.doanjv.Fragment_TinhTrangSucKhoe;

import java.util.Calendar;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                return new Fragment_ThongTin();
            case 1:
                return new Fragment_TinhTrangSucKhoe();
            default:
                return new Fragment_ThongTin();
        }
    }
    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position)
        {
            case 0:
                title = "Thông tin cá nhân";
                break;
            case 1:
                title = "Tình trạng sức khỏe";
                break;
        }
        return title;
    }
}
