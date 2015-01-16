package com.f2m02dr0.io.gpio;

import java.util.Map;

public abstract interface GpioPin
{
  public abstract GpioProvider getProvider();
  
  public abstract Pin getPin();
  
  public abstract void setName(String paramString);
  
  public abstract String getName();
  
  public abstract void setTag(Object paramObject);
  
  public abstract Object getTag();
  
  public abstract void setProperty(String paramString1, String paramString2);
  
  public abstract boolean hasProperty(String paramString);
  
  public abstract String getProperty(String paramString);
  
  public abstract String getProperty(String paramString1, String paramString2);
  
  public abstract Map<String, String> getProperties();
  
  public abstract void removeProperty(String paramString);
  
  public abstract void clearProperties();
  
  public abstract void export(PinMode paramPinMode);
  
  public abstract void unexport();
  
  public abstract boolean isExported();
  
  public abstract void setMode(PinMode paramPinMode);
  
  public abstract PinMode getMode();
  
  public abstract boolean isMode(PinMode paramPinMode);
  
  public abstract void setPullResistance(PinPullResistance paramPinPullResistance);
  
  public abstract PinPullResistance getPullResistance();
  
  public abstract boolean isPullResistance(PinPullResistance paramPinPullResistance);
  
  public abstract GpioPinShutdown getShutdownOptions();
  
  public abstract void setShutdownOptions(GpioPinShutdown paramGpioPinShutdown);
  
  public abstract void setShutdownOptions(Boolean paramBoolean);
  
  public abstract void setShutdownOptions(Boolean paramBoolean, PinState paramPinState);
  
  public abstract void setShutdownOptions(Boolean paramBoolean, PinState paramPinState, PinPullResistance paramPinPullResistance);
  
  public abstract void setShutdownOptions(Boolean paramBoolean, PinState paramPinState, PinPullResistance paramPinPullResistance, PinMode paramPinMode);
}
