# Microsoft Developer Studio Project File - Name="log4shib" - Package Owner=<4>
# Microsoft Developer Studio Generated Build File, Format Version 6.00
# ** DO NOT EDIT **

# TARGTYPE "Win32 (x86) Static Library" 0x0104

CFG=log4shib - Win32 Debug
!MESSAGE This is not a valid makefile. To build this project using NMAKE,
!MESSAGE use the Export Makefile command and run
!MESSAGE 
!MESSAGE NMAKE /f "log4shib.mak".
!MESSAGE 
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "log4shib.mak" CFG="log4shib - Win32 Debug"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "log4shib - Win32 Release" (based on "Win32 (x86) Static Library")
!MESSAGE "log4shib - Win32 Debug" (based on "Win32 (x86) Static Library")
!MESSAGE 

# Begin Project
# PROP AllowPerConfigDependencies 0
# PROP Scc_ProjName ""
# PROP Scc_LocalPath ""
CPP=cl.exe
RSC=rc.exe

!IF  "$(CFG)" == "log4shib - Win32 Release"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 0
# PROP BASE Output_Dir "Release"
# PROP BASE Intermediate_Dir "Release"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 0
# PROP Output_Dir "Release"
# PROP Intermediate_Dir "Release"
# PROP Target_Dir ""
MTL=midl.exe
# ADD BASE CPP /nologo /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_MBCS" /D "_LIB" /YX /FD /c
# ADD CPP /nologo /MD /W3 /GX /O2 /I "..\..\include" /D "WIN32" /D "NDEBUG" /D "_MBCS" /D "_LIB" /D "LOG4SHIB_HAS_DLL" /D "LOG4SHIB_BUILD_DLL" /FR /YX /FD /c
# ADD BASE RSC /l 0x409 /d "NDEBUG"
# ADD RSC /l 0x409 /d "NDEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LIB32=link.exe -lib
# ADD BASE LIB32 /nologo
# ADD LIB32 /nologo

!ELSEIF  "$(CFG)" == "log4shib - Win32 Debug"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 1
# PROP BASE Output_Dir "Debug"
# PROP BASE Intermediate_Dir "Debug"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 1
# PROP Output_Dir "Debug"
# PROP Intermediate_Dir "Debug"
# PROP Target_Dir ""
MTL=midl.exe
# ADD BASE CPP /nologo /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_MBCS" /D "_LIB" /YX /FD /GZ /c
# ADD CPP /nologo /MDd /W3 /Gm /GX /ZI /Od /I "..\..\include" /D "WIN32" /D "_DEBUG" /D "_MBCS" /D "_LIB" /D "LOG4SHIB_HAS_DLL" /D "LOG4SHIB_BUILD_DLL" /FR /YX /FD /GZ /c
# ADD BASE RSC /l 0x409 /d "_DEBUG"
# ADD RSC /l 0x409 /d "_DEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LIB32=link.exe -lib
# ADD BASE LIB32 /nologo
# ADD LIB32 /nologo /out:"Debug\log4shibD.lib"

!ENDIF 

# Begin Target

# Name "log4shib - Win32 Release"
# Name "log4shib - Win32 Debug"
# Begin Group "Source Files"

# PROP Default_Filter "cpp;c;cxx;rc;def;r;odl;idl;hpj;bat"
# Begin Source File

SOURCE=..\..\src\Appender.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\AppenderSkeleton.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\BasicConfigurator.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\BasicLayout.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\Category.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\CategoryStream.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\Configurator.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\DummyThreads.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\FileAppender.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\Filter.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\FixedContextCategory.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\HierarchyMaintainer.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\IdsaAppender.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\LayoutAppender.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\LoggingEvent.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\MSThreads.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\NDC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\NTEventLogAppender.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\OmniThreads.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\OstreamAppender.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\PatternLayout.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\PortabilityImpl.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\Priority.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\Properties.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\PropertyConfigurator.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\PropertyConfiguratorImpl.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\PThreads.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\RemoteSyslogAppender.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\RollingFileAppender.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\SimpleConfigurator.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\SimpleLayout.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\StringQueueAppender.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\StringUtil.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\TimeStamp.cpp
# End Source File
# Begin Source File

SOURCE=..\..\src\Win32DebugAppender.cpp
# End Source File
# End Group
# Begin Group "Header Files"

# PROP Default_Filter "h;hh;hpp;hxx;hm;inl"
# Begin Source File

SOURCE=..\src\PortabilityImpl.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\Appender.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\AppenderSkeleton.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\BasicConfigurator.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\BasicLayout.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\threading\BoostThreads.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\Category.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\CategoryStream.hh
# End Source File
# Begin Source File

SOURCE="..\..\include\log4shib\config-win32.h"
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\Configurator.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\ConfiguratorSkeleton.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\threading\DummyThreads.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\Export.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\FileAppender.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\Filter.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\FixedContextCategory.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\HierarchyMaintainer.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\IdsaAppender.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\Layout.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\LayoutAppender.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\LoggingEvent.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\threading\MSThreads.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\NDC.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\NTEventLogAppender.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\threading\OmniThreads.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\OstreamAppender.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\PatternLayout.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\Portability.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\Priority.hh
# End Source File
# Begin Source File

SOURCE=..\..\src\Properties.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\PropertyConfigurator.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\RemoteSyslogAppender.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\RollingFileAppender.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\SimpleConfigurator.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\SimpleLayout.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\StringQueueAppender.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\SyslogAppender.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\threading\Threading.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\TimeStamp.hh
# End Source File
# Begin Source File

SOURCE=..\..\include\log4shib\Win32DebugAppender.hh
# End Source File
# End Group
# Begin Source File

SOURCE=..\NTEventLogCategories.mc

!IF  "$(CFG)" == "log4shib - Win32 Release"

# Begin Custom Build
OutDir=.\Release
ProjDir=.
InputPath=..\NTEventLogCategories.mc
InputName=NTEventLogCategories

"$(OutDir)\NTEventLogAppender.dll" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	if not exist $(OutDir) md $(OutDir) 
	"$(MSDEVDIR)\..\..\VC98\Bin\mc.exe" -h $(OutDir) -r $(OutDir) $(projdir)\..\$(InputName).mc 
	"$(MSDEVDIR)\Bin\RC.exe" -r -fo $(OutDir)\$(InputName).res $(OutDir)\$(InputName).rc 
	"$(MSDEVDIR)\..\..\VC98\Bin\link.exe" /MACHINE:IX86 -dll -noentry -out:$(OutDir)\NTEventLogAppender.dll $(OutDir)\$(InputName).res 
	
# End Custom Build

!ELSEIF  "$(CFG)" == "log4shib - Win32 Debug"

# Begin Custom Build
OutDir=.\Debug
ProjDir=.
InputPath=..\NTEventLogCategories.mc
InputName=NTEventLogCategories

"$(OutDir)\NTEventLogAppender.dll" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	if not exist $(OutDir) md $(OutDir) 
	"$(MSDEVDIR)\..\..\VC98\Bin\mc.exe" -h $(OutDir) -r $(OutDir) $(projdir)\..\$(InputName).mc 
	"$(MSDEVDIR)\Bin\RC.exe" -r -fo $(OutDir)\$(InputName).res $(OutDir)\$(InputName).rc 
	"$(MSDEVDIR)\..\..\VC98\Bin\link.exe" /MACHINE:IX86 -dll -noentry -out:$(OutDir)\NTEventLogAppender.dll $(OutDir)\$(InputName).res 
	
# End Custom Build

!ENDIF 

# End Source File
# End Target
# End Project
