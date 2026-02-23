package com.taranasubscriptionmanager.modelviewui.dashboard;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.taranasubscriptionmanager.data.local.AppDatabase;

import java.time.LocalDate;
import java.time.YearMonth;

public class DashboardViewModel extends AndroidViewModel {

    private final AppDatabase db;

    private final MutableLiveData<Integer>todayQuantity = new MutableLiveData<>();
    private final MutableLiveData<Double>todayRevenue=new MutableLiveData<>();
    private final MutableLiveData<Double>monthlyRevenue=new MutableLiveData<>();
    private final MutableLiveData<Integer>activeUsers=new MutableLiveData<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DashboardViewModel(@NonNull Application application){
        super(application);
        db=AppDatabase.getDatabase(application);
        loadDashboardData();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadDashboardData(){
        new Thread(()->{
            long today =getTodayDate();
            long start = getStartOfMonth();
            long end=getEndOfMonth();

            int qty =db.deliveryDao().getTodayTotal(today);
            double monthRev=db.deliveryDao().getMonthlyRevenue(start,end);

            Integer userCount =db.userDao().getActiveUsersCount();

            todayQuantity.postValue(qty);
            monthlyRevenue.postValue(monthRev);
            activeUsers.postValue(userCount);

            Double todayRev=db.deliveryDao().getTodayRevenue(today);
            todayRevenue.postValue(todayRev);
        }).start();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private long getTodayDate(){
        LocalDate date=LocalDate.now();
        return Long.parseLong(date.toString().replace("_",""));

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private long getStartOfMonth(){
        YearMonth ym=YearMonth.now();
        return Long.parseLong(ym.atDay(1).toString().replace("_",""));
    }

@RequiresApi(api = Build.VERSION_CODES.O)
    private long getEndOfMonth(){
        YearMonth ym=YearMonth.now();
        return Long.parseLong(ym.atEndOfMonth().toString().replace("_",""));

}

public LiveData<Integer>getTodayQuantity(){
        return todayQuantity;
}

public LiveData<Double>getTodayRevenue(){
        return todayRevenue;
}

public LiveData<Double>getMonthlyRevenue(){
        return monthlyRevenue;
}

public LiveData<Integer>getActiveUsers(){
        return activeUsers;
}


}
