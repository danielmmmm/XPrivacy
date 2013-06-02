package biz.bokhorst.xprivacy;

import android.content.Context;
import android.util.Log;

import de.robv.android.xposed.XC_MethodHook.MethodHookParam;

public abstract class XHook {

	private String mMethodName;
	private String mRestrictionName;

	public XHook(String methodName, String restrictionName) {
		mMethodName = methodName;
		mRestrictionName = restrictionName;
	}

	public String getMethodName() {
		return mMethodName;
	}

	abstract protected void before(MethodHookParam param) throws Throwable;

	abstract protected void after(MethodHookParam param) throws Throwable;

	abstract protected boolean isRestricted(MethodHookParam param) throws Throwable;

	protected boolean getRestricted(Context context, int uid, boolean usage) {
		return XRestriction.getRestricted(this, context, uid, mRestrictionName, usage);
	}

	protected void setRestricted(Context context, int uid, boolean restricted) {
		XRestriction.setRestricted(this, context, uid, mRestrictionName, restricted);
	}

	protected void info(String message) {
		XUtil.log(this, Log.INFO, message);
	}

	protected void warning(String message) {
		XUtil.log(this, Log.WARN, message);
	}

	protected void error(String message) {
		XUtil.log(this, Log.ERROR, message);
	}
}
