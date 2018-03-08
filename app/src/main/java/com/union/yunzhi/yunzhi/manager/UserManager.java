package com.union.yunzhi.yunzhi.manager;


import android.graphics.drawable.Drawable;

import com.union.yunzhi.factories.moudles.me.BaseMeModel;
import com.union.yunzhi.factories.moudles.me.PersonModel;
import com.union.yunzhi.yunzhi.R;

/**
 * @description 单例管理登陆用户信息
 *
 * @date
 */
public class UserManager {

	private static UserManager userManager = null;
	private BaseMeModel user = null;
	private PersonModel mPerson = null;

	public static UserManager getInstance() {

		if (userManager == null) {

			synchronized (UserManager.class) {

				if (userManager == null) {

					userManager = new UserManager();
				}
				return userManager;
			}
		} else {

			return userManager;
		}
	}

	/**
	 * init the user
	 *
	 * @param user
	 */
	public void setUser(BaseMeModel user) {

		this.user = user;
		this.mPerson = user.data.getPersonModel();
	}

	public boolean hasLogined() {

		return user == null ? false : true;

	}

	/**
	 * has user info
	 *
	 * @return
	 */
	public BaseMeModel getUser() {

		return this.user;
	}

	public PersonModel getPerson() {
		return mPerson;
	}

	/**
	 * remove the user info
	 */
	public void removeUser() {

		this.user = null;
		mPerson = null;
	}
}
