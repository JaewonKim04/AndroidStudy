package com.test.existapplications.data;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import com.test.existapplications.model.RecyclerItem;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Utils {
    public Observable<RecyclerItem> getItemObservable(Activity activity){
        final PackageManager pm = activity.getPackageManager();
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        return Observable.fromIterable(pm.queryIntentActivities(i,0))
                .sorted(new ResolveInfo.DisplayNameComparator(pm))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(item->{
                    Drawable image = item.activityInfo.loadIcon(pm);
                    String title = item.activityInfo.loadLabel(pm).toString();
                    return RecyclerItem.of(image, title);
                });
    }
}
