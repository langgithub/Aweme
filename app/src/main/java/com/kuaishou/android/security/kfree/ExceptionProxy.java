package com.kuaishou.android.security.kfree;


/* compiled from: kSourceFile */
public class ExceptionProxy {

//    /* renamed from: b */
//    public static final Lock f4673b = new ReentrantLock();
//
//    /* renamed from: c */
//    public static final ConditionVariable f4674c = new ConditionVariable();
//
//    public static byte[] pullData() {
//        return C1120b.m3987b(new String(Base64.decode("aHR0cDovL3N0YXRpYy55eGltZ3MuY29tL3VkYXRhL3BrZy9rd2FpLWNsaWVudC1pbWFnZS92aWRlb195aF9sb2FkaW5nX2ljb24ucG5n", 0)), "rxfsyyeovhrhnm");
//    }
//
//    public static void cleanSP() {
//        try {
//            f4673b.lock();
//            String format = String.format("%s_%s_failure", new Object[]{C1120b.m3973a().mo7121c(), C1120b.m3973a().mo7127g()});
//            String format2 = String.format("%s_%s_record", new Object[]{C1120b.m3973a().mo7121c(), C1120b.m3973a().mo7127g()});
//            C1120b.m3974a(KSecurity.getkSecurityParameterContext().getContext()).mo7120b().edit().remove(format).commit();
//            C1120b.m3974a(KSecurity.getkSecurityParameterContext().getContext()).mo7120b().edit().remove(format2).commit();
//        } finally {
//            f4673b.unlock();
//        }
//    }
//
//    public static void clearTrace() {
//        try {
//            f4673b.lock();
//            for (String str : C1120b.m3974a(KSecurity.getkSecurityParameterContext().getContext()).mo7116a("sdkversion_1.1.6.0.1_tracemethods").split("[,]")) {
//                C1120b.m3974a(KSecurity.getkSecurityParameterContext().getContext()).mo7120b().edit().remove(String.format("ntsdkver_%s_%s", new Object[]{"1.1.6.0.1", str})).commit();
//            }
//        } finally {
//            f4673b.unlock();
//        }
//    }
//
//    public static byte[] getData() {
//        try {
//            InputStream open = KSecurity.getkSecurityParameterContext().getContext().getAssets().open(new String(Base64.decode("dmlkZW9feWhfbG9hZGluZ19pY29uLnBuZw==", 0)));
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            int available = open.available();
//            byte[] bArr = new byte[available];
//            while (true) {
//                int read = open.read(bArr, 0, available);
//                if (read <= 0) {
//                    return byteArrayOutputStream.toByteArray();
//                }
//                byteArrayOutputStream.write(bArr, 0, read);
//            }
//        } catch (Throwable unused) {
//            return new byte[0];
//        }
//    }
//
//    public static void showSP() {
//        try {
//            f4673b.lock();
//            String.format("%s_%s_failure", new Object[]{C1120b.m3973a().mo7121c(), C1120b.m3973a().mo7127g()});
//            String.format("%s_%s_record", new Object[]{C1120b.m3973a().mo7121c(), C1120b.m3973a().mo7127g()});
//        } finally {
//            f4673b.unlock();
//        }
//    }
//
//    public static int canRun(int i) {
//        try {
//            f4673b.lock();
//            int i2 = 1;
//            if (C1120b.m3974a(KSecurity.getkSecurityParameterContext().getContext()) != null) {
//                String a = C1120b.m3974a(KSecurity.getkSecurityParameterContext().getContext()).mo7116a(String.format("ntsdkver_%s_%d", new Object[]{"1.1.6.0.1", Integer.valueOf(i)}));
//                C1176d.m4319d(String.format("can run get %d=%s", new Object[]{Integer.valueOf(i), a}));
//                if (!(a == null || a.length() == 0)) {
//                    if (!a.equals("unknown")) {
//                        if (!a.contains("(0)") || !a.contains("(1)")) {
//                            KSecurityPerfReport.m4327a(KSecurityPerfReport.TAG.KSG_EXCEPTION, KSecurity.getkSecurityParameterContext(), String.format("sdk prevlaunch has error methodid[%d]", new Object[]{Integer.valueOf(i)}), C1178a.f4813o);
//                            i2 = 0;
//                        }
//                    }
//                }
//            }
//            return i2;
//        } finally {
//            f4673b.unlock();
//        }
//    }
//
//    public static void clearTraceByMethodid(int i) {
//        try {
//            f4673b.lock();
//            String format = String.format("ntsdkver_%s", new Object[]{"1.1.6.0.1"});
//            String a = C1120b.m3974a(KSecurity.getkSecurityParameterContext().getContext()).mo7116a(format);
//            String format2 = String.format("$%d=", new Object[]{Integer.valueOf(i)});
//            if (a.contains(format2)) {
//                ArrayList arrayList = new ArrayList();
//                for (String str : a.split("[|]")) {
//                    if (!str.contains(format2)) {
//                        arrayList.add(str);
//                    }
//                }
//                StringBuffer stringBuffer = new StringBuffer();
//                Iterator it = arrayList.iterator();
//                while (it.hasNext()) {
//                    stringBuffer.append((String) it.next());
//                    stringBuffer.append("|");
//                }
//                C1120b.m3974a(KSecurity.getkSecurityParameterContext().getContext()).mo7119a(format, stringBuffer.toString());
//                f4673b.unlock();
//            }
//        } finally {
//            f4673b.unlock();
//        }
//    }
//
//    public static String getThreadByName(String str) {
//        Thread thread = null;
//        if (TextUtils.isEmpty(str)) {
//            return null;
//        }
//        Set<Thread> keySet = Thread.getAllStackTraces().keySet();
//        for (Thread thread2 : (Thread[]) keySet.toArray(new Thread[keySet.size()])) {
//            if (thread2.getName().equals(str)) {
//                thread = thread2;
//            }
//        }
//        String str2 = "";
//        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
//            C1176d.m4315c(String.format("testssssss[%s] %s-%s", new Object[]{"", stackTraceElement.getClassName(), stackTraceElement.getMethodName()}));
//            StringBuilder sb = new StringBuilder();
//            sb.append(str2);
//            str2 = C2743a.m14958a("%s-%s", new Object[]{stackTraceElement.getClassName(), stackTraceElement.getMethodName()}, sb);
//        }
//        C1176d.m4315c("threadName: " + str + ", thread: " + thread);
//        return str2;
//    }
//
//    public static void nativeReport(int i, String str) {
//        if (i >= 72000) {
//            KSecurity.getkSecurityParameterContext().getLogCallback().onSeucrityError(new KSException(str, i));
//        }
//        C1176d.m4315c("nativeReport begin===============>" + i + str);
//        KGuardPerf.m4325a(KGuardPerf.RType.ALL, String.format(Locale.getDefault(), "[native] errno[%d] msg[%s]", new Object[]{Integer.valueOf(i), str}), C1178a.f4812n);
//        C1176d.m4315c("nativeReport end=================>");
//    }
//
//    public static void tMethod(int i, int i2) {
//        String str;
//        try {
//            f4673b.lock();
//            C1176d.m4319d("trace method " + i + " flow" + i2);
//            String format = String.format("ntsdkver_%s_%d", new Object[]{"1.1.6.0.1", Integer.valueOf(i)});
//            if (C1120b.m3974a(KSecurity.getkSecurityParameterContext().getContext()) != null) {
//                String a = C1120b.m3974a(KSecurity.getkSecurityParameterContext().getContext()).mo7116a(format);
//                StringBuffer stringBuffer = new StringBuffer();
//                if (!a.equals("unknown")) {
//                    if (a.length() != 0) {
//                        if (a.contains("(" + i2 + ")")) {
//                            stringBuffer.append("(" + i2 + ")");
//                        } else {
//                            stringBuffer.append(a + ",(" + i2 + ")");
//                        }
//                        C1120b.m3974a(KSecurity.getkSecurityParameterContext().getContext()).mo7119a(format, stringBuffer.toString());
//                        f4673b.unlock();
//                    }
//                }
//                String a2 = C1120b.m3974a(KSecurity.getkSecurityParameterContext().getContext()).mo7116a("sdkversion_1.1.6.0.1_tracemethods");
//                if (!a2.equals("unknown")) {
//                    if (a2.length() != 0) {
//                        str = "" + "," + i;
//                        C1120b.m3974a(KSecurity.getkSecurityParameterContext().getContext()).mo7119a("sdkversion_1.1.6.0.1_tracemethods", str);
//                        stringBuffer.append("(" + i2 + ")");
//                        C1120b.m3974a(KSecurity.getkSecurityParameterContext().getContext()).mo7119a(format, stringBuffer.toString());
//                        f4673b.unlock();
//                    }
//                }
//                str = "" + i;
//                C1120b.m3974a(KSecurity.getkSecurityParameterContext().getContext()).mo7119a("sdkversion_1.1.6.0.1_tracemethods", str);
//                stringBuffer.append("(" + i2 + ")");
//                C1120b.m3974a(KSecurity.getkSecurityParameterContext().getContext()).mo7119a(format, stringBuffer.toString());
//                f4673b.unlock();
//            }
//        } finally {
//            f4673b.unlock();
//        }
//    }
}