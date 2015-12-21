/*
 * The MIT License
 * 
 * Copyright (C) 2015 Kiyofumi Kondoh
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package jp.ne.sakura.kkkon.android.unitynofullscreen;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;

public class UnityNoFullscreen
{
    private static final String TAG = "kk-unitynofullscreen";

    private static final String CONST_UNITYPALYER_NAME = "com.unity3d.player.UnityPlayer";
    private static final String CONST_UNITYPALYER_METHOD_SETFULLSCREEN = "setFullscreen";
    private static final String CONST_UNITYPALYER_FIELD_CURRENTACTIVITY = "currentActivity";
    private static final String CONST_UNITYPALYER_FIELD_UNITYPLAYER = "mUnityPlayer";

    public static boolean setFullscreen( final Object mUnityPlayer, final boolean isFullscreen )
    {
        Log.d( TAG, "enter" );
        boolean result = true;

        try
        {
            if ( null == mUnityPlayer )
            {
                Log.d( TAG, "mUnityPlayer is null" );
                return false;
            }

            final Class<?> clz = mUnityPlayer.getClass();
            if ( null == clz )
            {
                Log.d( TAG, "clz is null" );
                return false;
            }

            // check class name
            if ( null != clz )
            {
                final String clzName = clz.getName();
                if ( null == clzName )
                {
                    Log.d( TAG, "" );
                    return false;
                }

                if ( 0 != CONST_UNITYPALYER_NAME.compareTo(clzName) )
                {
                    Log.d( TAG, "" );
                    return false;
                }
            }

            {
                /*
                if ( null != clz )
                {
                    final java.lang.reflect.Method[] methods = clz.getMethods();
                    if ( null != methods )
                    {
                        for( final java.lang.reflect.Method method : methods )
                        {
                            if ( null != method )
                            {
                                Log.d( TAG, "methods: " + method.getName() );
                            }
                        }
                    }
                }
                */
                /*
                if ( null != clz )
                {
                    final java.lang.reflect.Method[] methods = clz.getDeclaredMethods();
                    if ( null != methods )
                    {
                        for( final java.lang.reflect.Method method : methods )
                        {
                            if ( null != method )
                            {
                                Log.d( TAG, "methods: " + method.getName() );
                            }
                        }
                    }
                }
                */
            }

            java.lang.reflect.Method method = null;
            try
            {
                //method = clz.getMethod( CONST_UNITYPALYER_METHOD_SETFULLSCREEN, new Class[] { boolean.class } );
                method = clz.getDeclaredMethod( CONST_UNITYPALYER_METHOD_SETFULLSCREEN, new Class[] { boolean.class } );
            }
            catch ( NoSuchMethodException e )
            {
                Log.d( TAG, "", e );
                return false;
            }
            catch ( SecurityException e )
            {
                Log.d( TAG, "", e );
                return false;
            }

            if ( null == method )
            {
                Log.d( TAG, "method is null" );
            }
            else
            {
                try
                {
                    method.setAccessible( true );
                }
                catch ( java.security.AccessControlException e )
                {
                    Log.d( TAG, "", e );
                    return false;
                }
            }

            if ( null != method )
            {
                Object retValue = null;
                try
                {
                    retValue = method.invoke( mUnityPlayer, new Object[] { isFullscreen } );
                }
                catch ( IllegalAccessException e )
                {
                    Log.d( TAG, "", e );
                    return false;
                }
                catch ( IllegalArgumentException e )
                {
                    Log.d( TAG, "", e );
                    return false;
                }
                catch ( InvocationTargetException e )
                {
                    Log.d( TAG, "", e );
                    return false;
                }
            }

        }
        catch ( Exception e )
        {
            Log.d( TAG, "", e );
            result = false;
        }

        Log.d( TAG, "result=" + result );
        return result;
    }

    public static boolean setFullscreen( final boolean isFullscreen )
    {
        Log.d( TAG, "enter" );
        boolean result = true;

        try
        {
            Class<?> clz = null;
            try
            {
                clz = Class.forName( CONST_UNITYPALYER_NAME );
            }
            catch ( ClassNotFoundException e )
            {
                Log.d( TAG, "", e );
                return false;
            }

            java.lang.reflect.Field fieldCurrentActivity = null;
            try
            {
                fieldCurrentActivity = clz.getField( CONST_UNITYPALYER_FIELD_CURRENTACTIVITY );
            }
            catch ( NoSuchFieldException e )
            {
                Log.d( TAG, "", e );
                return false;
            }
            catch ( SecurityException e )
            {
                Log.d( TAG, "", e );
                return false;
            }

            Object objActivity = null;
            try
            {
                objActivity = fieldCurrentActivity.get(null);
            }
            catch ( IllegalAccessException e )
            {
                Log.d( TAG, "", e );
                return false;
            }
            catch ( IllegalArgumentException e )
            {
                Log.d( TAG, "", e );
                return false;
            }

            Class<?> clzActivity = null;
            if ( null != objActivity )
            {
                clzActivity = objActivity.getClass();
            }

            java.lang.reflect.Field fieldUnityPlayer = null;
            if ( null == clzActivity )
            {
                Log.d( TAG, "clzActivity is null" );
            }
            else
            {
                try
                {
                    fieldUnityPlayer = clzActivity.getDeclaredField( CONST_UNITYPALYER_FIELD_UNITYPLAYER );
                }
                catch ( NoSuchFieldException e )
                {
                    Log.d( TAG, "", e );
                    return false;
                }
                catch ( SecurityException e )
                {
                    Log.d( TAG, "", e );
                    return false;
                }
            }

            if ( null == fieldUnityPlayer )
            {
                Log.d( TAG, "fieldUnityPlayer is null" );
            }
            else
            {
                try
                {
                    fieldUnityPlayer.setAccessible( true );
                }
                catch ( SecurityException e )
                {
                    Log.d( TAG, "", e );
                    return false;
                }
            }

            Object objUnityPlayer = null;
            if ( null == fieldUnityPlayer )
            {
                Log.d( TAG, "fieldUnityPlayer is null" );
            }
            else
            {
                try
                {
                    objUnityPlayer = fieldUnityPlayer.get( objActivity );
                }
                catch ( IllegalAccessException e )
                {
                    Log.d( TAG, "", e );
                    return false;
                }
            }

            result = setFullscreen( objUnityPlayer, isFullscreen);
        }
        catch ( Exception e )
        {
            Log.d( TAG, "", e );
            result = false;
        }

        Log.d( TAG, "result=" + result );
        return result;
    }


}
