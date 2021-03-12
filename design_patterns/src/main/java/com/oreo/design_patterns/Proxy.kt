package com.oreo.design_patterns

import java.lang.Exception
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

interface UserDao {
    fun saveUser()
}

class UserDaoImpl : UserDao {
    override fun saveUser() {
        println(" ---- 保存用户 ---- ")
    }
}

class UserHandler(private val userDao: UserDao) : InvocationHandler {
    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
        saveUserStart()
        val obj:Any? = method?.invoke(userDao, args)
        saveUserDone()
        return obj!!
    }

    private fun saveUserStart() {
        println("---- 开始插入 ----");
    }

    private fun saveUserDone() {
        println("---- 插入完成 ----");
    }

    // JDK 动态代理， 有bug无法运行
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val userDao = UserDaoImpl()
            val handler = UserHandler(userDao)

            val loader = userDao.javaClass.classLoader
            val interfaces = userDao.javaClass.interfaces

            val proxy = (Proxy.newProxyInstance(loader, interfaces, handler)) as UserDao
            proxy.saveUser()
        }
    }
}

class UserProxy(private val userDao: UserDao) {

    fun saveUser() {
        println(" ---- 代理开始 ---- ")
        userDao.saveUser()
        println(" ---- 代理结束 ----")
    }

    // JDK 静态代理
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val userDao = UserDaoImpl()
            val userProxy = UserProxy(userDao)
            userProxy.saveUser()
        }
    }
}