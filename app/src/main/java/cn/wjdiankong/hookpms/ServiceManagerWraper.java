package cn.wjdiankong.hookpms;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by jiangwei1-g on 2016/9/7.
 */
public class ServiceManagerWraper {
	
    public static void hookPMS(Context context, String signed){
        try{
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = 
            		activityThreadClass.getDeclaredMethod("currentActivityThread");
            Object currentActivityThread = currentActivityThreadMethod.invoke(null);
            Field sPackageManagerField = activityThreadClass.getDeclaredField("sPackageManager");
            sPackageManagerField.setAccessible(true);
            Object sPackageManager = sPackageManagerField.get(currentActivityThread);
            Class<?> iPackageManagerInterface = Class.forName("android.content.pm.IPackageManager");
            Object proxy = Proxy.newProxyInstance(
                    iPackageManagerInterface.getClassLoader(),
                    new Class<?>[] { iPackageManagerInterface },
                    new PmsHookBinderInvocationHandler(sPackageManager, signed));
            sPackageManagerField.set(currentActivityThread, proxy);
            PackageManager pm = context.getPackageManager();
            Field mPmField = pm.getClass().getDeclaredField("mPM");
            mPmField.setAccessible(true);
            mPmField.set(pm, proxy);
        }catch (Exception e){
            Log.d("jw", "hook pms error:"+Log.getStackTraceString(e));
        }
    }
    
    public static void hookPMS(Context context){
    	String douyin_signStr = "3082024f308201b8a00302010202044e269662300d06092a864886f70d0101050500306b310b300906035504061302434e3110300e060355040813076265696a696e673110300e060355040713076265696a696e6731133011060355040a130a686563616f2e696e666f31133011060355040b130a686563616f2e696e666f310e300c0603550403130563616f68653020170d3131303732303038343833345a180f32303636303432323038343833345a306b310b300906035504061302434e3110300e060355040813076265696a696e673110300e060355040713076265696a696e6731133011060355040a130a686563616f2e696e666f31133011060355040b130a686563616f2e696e666f310e300c0603550403130563616f686530819f300d06092a864886f70d010101050003818d003081890281810093bce2a30779500e3a3160ce5b557f3fa34df50df25ac1ae38c181c8ad94e4709d00afbc532d27ccfd4a92c8f1bd5b19c1f04f37b8230020035e33eb39de2d482ad4c043f251fb08007cb3eac4a348e140a817784195f0fbafc7480c90f76ef966d220abd9c4ab3d246276c98ce6d77a7fcc4f451ae89eb387d9bff521898d970203010001300d06092a864886f70d0101050500038181001ce4eb9f42d76dfc4e0f5da07bc3efae2cf98b47a39790d35407f3aeb6b554cadd65e84c7252046b3ab72b2dfc86f0892e28fee3e6e4e801093e3a4f29bc560762d33839ceb29385583ded64548f245977d61925543dda7ac3d34e8153a88f9846f446ff96d4877ad808280bbd7c43b9bf5feea3dd8d6bd179bc8cf29f949163";
    	hookPMS(context, douyin_signStr);
    }
    
}
