package com.icbc.hexm.swu;

import java.util.Iterator;

import org.directwebremoting.Container;
import org.directwebremoting.ServerContext;
import org.directwebremoting.extend.CreatorManager;
import org.directwebremoting.impl.StartupUtil;

public class DwrTest {

	public String sayHello(String name) {
		// 可以是访问数据库的复杂代码
		return "Hello World! My Name is: " + name;
	}

	public static void test() {
//		ServerContext serverContext = ServerContextFactory.get();
		StartupUtil.outOfContainerInit();
		ServerContext serverContext = StartupUtil.getSingletonServerContext();
		Container Container = serverContext.getContainer();
		CreatorManager creatorManager = (CreatorManager) Container.getBean("org.directwebremoting.extend.CreatorManager");
		for (Iterator<?> i$ = creatorManager.getCreatorNames(false).iterator(); i$.hasNext();) {

			String name = (String) i$.next();
			System.out.println(name);
		}
	}
}
