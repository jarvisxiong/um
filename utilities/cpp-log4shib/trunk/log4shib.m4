# Configure paths for LOG4SHIB
# Owen Taylor     97-11-3

dnl AM_PATH_LOG4SHIB([MINIMUM-VERSION, [ACTION-IF-FOUND [, ACTION-IF-NOT-FOUND]]])
dnl Test for LOG4SHIB, and define LOG4SHIB_CFLAGS and LOG4SHIB_LIBS
dnl
AC_DEFUN([AM_PATH_LOG4SHIB],
[dnl 
dnl Get the cflags and libraries from the log4shib-config script
dnl
AC_ARG_WITH(log4shib-prefix,[  --with-log4shib-prefix=PFX   Prefix where LOG4SHIB is installed (optional)],
            log4shib_config_prefix="$withval", log4shib_config_prefix="")
AC_ARG_WITH(log4shib-exec-prefix,[  --with-log4shib-exec-prefix=PFX Exec prefix where LOG4SHIB is installed (optional)],
            log4shib_config_exec_prefix="$withval", log4shib_config_exec_prefix="")
AC_ARG_ENABLE(log4shibtest, [  --disable-log4shibtest       Do not try to compile and run a test LOG4SHIB program],
		    , enable_log4shibtest=yes)

  if test x$log4shib_config_exec_prefix != x ; then
     log4shib_config_args="$log4shib_config_args --exec-prefix=$log4shib_config_exec_prefix"
     if test x${LOG4SHIB_CONFIG+set} != xset ; then
        LOG4SHIB_CONFIG=$log4shib_config_exec_prefix/bin/log4shib-config
     fi
  fi
  if test x$log4shib_config_prefix != x ; then
     log4shib_config_args="$log4shib_config_args --prefix=$log4shib_config_prefix"
     if test x${LOG4SHIB_CONFIG+set} != xset ; then
        LOG4SHIB_CONFIG=$log4shib_config_prefix/bin/log4shib-config
     fi
  fi

  AC_PATH_PROG(LOG4SHIB_CONFIG, log4shib-config, no)
  min_log4shib_version=ifelse([$1], ,0.99.7,$1)
  AC_MSG_CHECKING(for LOG4SHIB - version >= $min_log4shib_version)
  no_log4shib=""
  if test "$LOG4SHIB_CONFIG" = "no" ; then
    no_log4shib=yes
  else
    LOG4SHIB_CFLAGS=`$LOG4SHIB_CONFIG $log4shib_config_args --cflags`
    LOG4SHIB_LIBS=`$LOG4SHIB_CONFIG $log4shib_config_args --libs`
    log4shib_config_major_version=`$LOG4SHIB_CONFIG $log4shib_config_args --version | \
           sed 's/\([[0-9]]*\).\([[0-9]]*\).\([[0-9]]*\)/\1/'`
    log4shib_config_minor_version=`$LOG4SHIB_CONFIG $log4shib_config_args --version | \
           sed 's/\([[0-9]]*\).\([[0-9]]*\).\([[0-9]]*\)/\2/'`
    log4shib_config_micro_version=`$LOG4SHIB_CONFIG $log4shib_config_args --version | \
           sed 's/\([[0-9]]*\).\([[0-9]]*\).\([[0-9]]*\)/\3/'`
    if test "x$enable_log4shibtest" = "xyes" ; then
      ac_save_CFLAGS="$CFLAGS"
      ac_save_CXXFLAGS="$CXXFLAGS"
      ac_save_LIBS="$LIBS"
      CFLAGS="$CFLAGS $LOG4SHIB_CFLAGS"
      CXXFLAGS="$CXXFLAGS $LOG4SHIB_CFLAGS"
      LIBS="$LOG4SHIB_LIBS $LIBS"
dnl
dnl Now check if the installed LOG4SHIB is sufficiently new. (Also sanity
dnl checks the results of log4shib-config to some extent
dnl
      rm -f conf.log4shibtest
      AC_TRY_RUN([
#include <log4shib/Category.hh>
#include <stdio.h>
#include <stdlib.h>

int 
main ()
{
  int log4shib_major, log4shib_minor, log4shib_micro;
  int major, minor, micro;
  char *tmp_version;
  char *tmp_log4shib_version;

  system ("touch conf.log4shibtest");

  /* HP/UX 9 (%@#!) writes to sscanf strings */
  tmp_version = strdup("$min_log4shib_version");
  if (sscanf(tmp_version, "%d.%d.%d", &major, &minor, &micro) != 3) {
     printf("%s, bad version string\n", "$min_log4shib_version");
     exit(1);
   }

   /* HP/UX 9 (%@#!) writes to sscanf strings */
   tmp_log4shib_version = strdup(LOG4SHIB_VERSION);
   if (sscanf(tmp_log4shib_version, "%d.%d.%d", &log4shib_major, &log4shib_minor, &log4shib_micro) != 3) {
     printf("%s, bad log4shib version string\n", LOG4SHIB_VERSION);
     exit(1);
   }

   if ((log4shib_major > major) ||
      ((log4shib_major == major) && (log4shib_minor > minor)) ||
      ((log4shib_major == major) && (log4shib_minor == minor) && (log4shib_micro >= micro)))
   {
        return 0;
   }
   else
   {
        printf("\n*** An old version of LOG4SHIB (%d.%d.%d) was found.\n",
               log4shib_major, log4shib_minor, log4shib_micro);
        printf("*** You need a version of LOG4SHIB newer than %d.%d.%d. The latest version of\n",
	       major, minor, micro);
        printf("***\n");
        printf("*** If you have already installed a sufficiently new version, this error\n");
        printf("*** probably means that the wrong copy of the log4shib-config shell script is\n");
        printf("*** being found. The easiest way to fix this is to remove the old version\n");
        printf("*** of LOG4SHIB, but you can also set the LOG4SHIB_CONFIG environment to point to the\n");
        printf("*** correct copy of log4shib-config. (In this case, you will have to\n");
        printf("*** modify your LD_LIBRARY_PATH enviroment variable, or edit /etc/ld.so.conf\n");
        printf("*** so that the correct libraries are found at run-time))\n");
   }

   log4shib::Category& log = log4shib::Category::getRoot();
   return 1;
}
],, no_log4shib=yes,[echo $ac_n "cross compiling; assumed OK... $ac_c"])
       CFLAGS="$ac_save_CFLAGS"
       CXXFLAGS="$ac_save_CXXFLAGS"
       LIBS="$ac_save_LIBS"
     fi
  fi
  if test "x$no_log4shib" = x ; then
     AC_MSG_RESULT(yes)
     ifelse([$2], , :, [$2])     
  else
     AC_MSG_RESULT(no)
if test "$LOG4SHIB_CONFIG" = "no" ; then
echo "*** The log4shib-config script installed by LOG4SHIB could not be found"
       echo "*** If LOG4SHIB was installed in PREFIX, make sure PREFIX/bin is in"
       echo "*** your path, or set the LOG4SHIB_CONFIG environment variable to the"
       echo "*** full path to log4shib-config."
     else
       if test -f conf.log4shibtest ; then
        :
       else
          echo "*** Could not run LOG4SHIB test program, checking why..."
          CFLAGS="$CFLAGS $LOG4SHIB_CFLAGS"
          CXXFLAGS="$CXXFLAGS $LOG4SHIB_CFLAGS"
          LIBS="$LIBS $LOG4SHIB_LIBS"
          AC_TRY_LINK([
#include <log4shib/Category.hh>
],      [ log4shib::Category::getRoot(); return 0; ],
        [ echo "*** The test program compiled, but did not run. This usually means"
          echo "*** that the run-time linker is not finding LOG4SHIB or finding the wrong"
          echo "*** version of LOG4SHIB. If it is not finding LOG4SHIB, you'll need to set your"
          echo "*** LD_LIBRARY_PATH environment variable, or edit /etc/ld.so.conf to point"
          echo "*** to the installed location  Also, make sure you have run ldconfig if that"
          echo "*** is required on your system"
	  echo "***"
          echo "*** If you have an old version installed, it is best to remove it, although"
          echo "*** you may also be able to get things to work by modifying LD_LIBRARY_PATH"
          echo "***" ],
        [ echo "*** The test program failed to compile or link. See the file config.log for the"
          echo "*** exact error that occured. This usually means LOG4SHIB was incorrectly installed"
          echo "*** or that you have moved LOG4SHIB since it was installed. In the latter case, you"
          echo "*** may want to edit the log4shib-config script: $LOG4SHIB_CONFIG" ])
          CFLAGS="$ac_save_CFLAGS"
          CXXFLAGS="$ac_save_CXXFLAGS"
          LIBS="$ac_save_LIBS"
       fi
     fi
     LOG4SHIB_CFLAGS=""
     LOG4SHIB_LIBS=""
     ifelse([$3], , :, [$3])
  fi
  AC_SUBST(LOG4SHIB_CFLAGS)
  AC_SUBST(LOG4SHIB_LIBS)
  rm -f conf.log4shibtest
])
