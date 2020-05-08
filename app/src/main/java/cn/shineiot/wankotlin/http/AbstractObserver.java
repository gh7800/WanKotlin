package cn.shineiot.wankotlin.http;

import android.content.Intent;
import android.text.TextUtils;
import com.google.gson.JsonParseException;
import org.json.JSONException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import cn.shineiot.base.mvp.BaseResult;
import cn.shineiot.base.utils.LogUtil;
import cn.shineiot.wankotlin.App;
import cn.shineiot.wankotlin.ui.login.LoginActivity;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * @author GF63
 * 错误信息集中处理
 * 登录过期，跳转登录页
 */
public abstract class AbstractObserver<T> implements Observer<BaseResult<T>> {
	//出错提示
	private final String networkMsg = "网络错误";
	private final String serverMsg = "服务器错误";
	private final String parameterMsg = "参数错误";
	private final String cookieOutMsg = "登录过期，请重新登录";
	private final String parseMsg = "服务器数据解析错误";
	private final String unknownMsg = "未知错误";
	private final String connectMsg = "连接服务器错误,请检查网络";
	private final String connectOutMsg = "连接服务器超时,请检查网络";

	@Override
	public void onSubscribe(Disposable d) {

	}

	@Override
	public void onComplete() {

	}

	@Override
	public void onNext(BaseResult<T> baseResult) {
		if (0 == baseResult.getErrorCode()) {            //成功获取数据
			requestSuccess(baseResult.getData());
		} else {                                  //失败
			if (!TextUtils.isEmpty(baseResult.getErrorMsg())){
				String errorMsg = baseResult.getErrorMsg();
				onError(new Throwable(errorMsg));
				if (errorMsg.contains("token") || errorMsg.contains("登录")) {  //未登录
					startLoginActivity();
				}
			} else {
				requestFaild("请求数据失败");
			}
		}
	}

	/**
	 * 打开loginActivity
	 */
	private void startLoginActivity() {
		Intent intent =new Intent(App.Companion.getContext(), LoginActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		App.Companion.getContext().startActivity(intent);
	}

	@Override
	public void onError(Throwable e) {
		Throwable throwable = e;
		//获取最根源的异常
		while (throwable.getCause() != null) {
			e = throwable;
			throwable = throwable.getCause();
		}
		String error = null;
		if (e instanceof ConnectException) {
			error = connectMsg;
		} else if (e instanceof HttpException) {            //HTTP错误
			HttpException httpException = (HttpException) e;
			switch (httpException.code()) {
				case 401:
					error = cookieOutMsg;
					break;
				case 404:
					error = parameterMsg;
					break;
				case 500:
					error = serverMsg;
					break;
				case 504:
					error = networkMsg;
					break;
				default:
					error = httpException.message();
					break;
			}
			LogUtil.Companion.e(httpException.code());
		} else if (e instanceof ApiException) {                //服务器返回的错误
			ApiException apiException = (ApiException) e;
//			LogUtil.Companion.e(apiException.getErrorCode());
			switch (apiException.getErrorCode()) {
				case "401":
					error = parameterMsg;
					break;
				case "10007":
					error = parseMsg;
					break;
				case "10008":
				case "11111":
					error = cookieOutMsg;
					break;
				default:
					error = e.getLocalizedMessage();
			}
		} else if (e instanceof JsonParseException || e instanceof JSONException) {
			error = parseMsg;
		} else if (e instanceof IOException) {                  //连接超时
			if (e instanceof SocketTimeoutException) {
				error = connectOutMsg;
			} else {
				if ("Canceled".equals(e.getMessage()) || "Socket closed".equals(e.getMessage())) {
					return;
				} else {
					error = connectMsg;
				}
			}
		} else {
			error = e.getLocalizedMessage();
		}

		if (error.contains("token") || error.contains("登录")) {  //未登录
			startLoginActivity();
		}
		try {
			//LogUtil.e("error--" + error);
			requestFaild(error);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}


	/**
	 * 成功
	 *
	 * @param t
	 */
	protected abstract void requestSuccess(T t);

	/**
	 * 请求失败
	 *
	 * @param error
	 */
	protected abstract void requestFaild(String error);
}
