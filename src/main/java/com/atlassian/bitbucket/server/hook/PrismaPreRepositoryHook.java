package com.atlassian.bitbucket.server.hook;

import com.atlassian.bitbucket.hook.*;
import com.atlassian.bitbucket.hook.repository.*;
import com.atlassian.bitbucket.repository.*;

import java.util.Collection;

import java.util.Collection;
import java.util.Calendar;

public class PrismaPreRepositoryHook implements PreReceiveRepositoryHook {
	/**
	 * Disables deletion of branches
	 */
	@Override
	public boolean onReceive(RepositoryHookContext context, Collection<RefChange> refChanges,
			HookResponse hookResponse) {

		Calendar now = Calendar.getInstance();

		boolean isFriday = now.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
		boolean isAfter4pm = now.get(Calendar.HOUR_OF_DAY) > 16;

		if (isFriday && isAfter4pm) {
			hookResponse.err().println("you cant push changes after 4am on friday");
			return false;
		}
		hookResponse.out().println("success");
		return true;
	}
}
