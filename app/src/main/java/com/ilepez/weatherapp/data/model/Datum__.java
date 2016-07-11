
package com.ilepez.weatherapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum__ implements Parcelable {

    @SerializedName("time")
    @Expose
    private int time;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("sunriseTime")
    @Expose
    private int sunriseTime;
    @SerializedName("sunsetTime")
    @Expose
    private int sunsetTime;
    @SerializedName("moonPhase")
    @Expose
    private double moonPhase;
    @SerializedName("precipIntensity")
    @Expose
    private double precipIntensity;
    @SerializedName("precipIntensityMax")
    @Expose
    private Double precipIntensityMax;
    @SerializedName("precipProbability")
    @Expose
    private Double precipProbability;
    @SerializedName("temperatureMin")
    @Expose
    private double temperatureMin;
    @SerializedName("temperatureMinTime")
    @Expose
    private int temperatureMinTime;
    @SerializedName("temperatureMax")
    @Expose
    private double temperatureMax;
    @SerializedName("temperatureMaxTime")
    @Expose
    private int temperatureMaxTime;
    @SerializedName("apparentTemperatureMin")
    @Expose
    private double apparentTemperatureMin;
    @SerializedName("apparentTemperatureMinTime")
    @Expose
    private int apparentTemperatureMinTime;
    @SerializedName("apparentTemperatureMax")
    @Expose
    private double apparentTemperatureMax;
    @SerializedName("apparentTemperatureMaxTime")
    @Expose
    private int apparentTemperatureMaxTime;
    @SerializedName("dewPoint")
    @Expose
    private double dewPoint;
    @SerializedName("humidity")
    @Expose
    private double humidity;
    @SerializedName("windSpeed")
    @Expose
    private double windSpeed;
    @SerializedName("windBearing")
    @Expose
    private int windBearing;
    @SerializedName("visibility")
    @Expose
    private Double visibility;
    @SerializedName("cloudCover")
    @Expose
    private Double cloudCover;
    @SerializedName("pressure")
    @Expose
    private double pressure;
    @SerializedName("ozone")
    @Expose
    private double ozone;
    @SerializedName("precipIntensityMaxTime")
    @Expose
    private int precipIntensityMaxTime;
    @SerializedName("precipType")
    @Expose
    private String precipType;

    protected Datum__(Parcel in) {
        time = in.readInt();
        summary = in.readString();
        icon = in.readString();
        sunriseTime = in.readInt();
        sunsetTime = in.readInt();
        moonPhase = in.readDouble();
        precipIntensity = in.readDouble();
        temperatureMin = in.readDouble();
        temperatureMinTime = in.readInt();
        temperatureMax = in.readDouble();
        temperatureMaxTime = in.readInt();
        apparentTemperatureMin = in.readDouble();
        apparentTemperatureMinTime = in.readInt();
        apparentTemperatureMax = in.readDouble();
        apparentTemperatureMaxTime = in.readInt();
        dewPoint = in.readDouble();
        humidity = in.readDouble();
        windSpeed = in.readDouble();
        windBearing = in.readInt();
        pressure = in.readDouble();
        ozone = in.readDouble();
        precipIntensityMaxTime = in.readInt();
        precipType = in.readString();
    }

    public static final Creator<Datum__> CREATOR = new Creator<Datum__>() {
        @Override
        public Datum__ createFromParcel(Parcel in) {
            return new Datum__(in);
        }

        @Override
        public Datum__[] newArray(int size) {
            return new Datum__[size];
        }
    };

    /**
     * 
     * @return
     *     The time
     */
    public int getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The time
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * 
     * @return
     *     The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 
     * @param summary
     *     The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 
     * @return
     *     The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 
     * @param icon
     *     The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 
     * @return
     *     The sunriseTime
     */
    public int getSunriseTime() {
        return sunriseTime;
    }

    /**
     * 
     * @param sunriseTime
     *     The sunriseTime
     */
    public void setSunriseTime(int sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    /**
     * 
     * @return
     *     The sunsetTime
     */
    public int getSunsetTime() {
        return sunsetTime;
    }

    /**
     * 
     * @param sunsetTime
     *     The sunsetTime
     */
    public void setSunsetTime(int sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    /**
     * 
     * @return
     *     The moonPhase
     */
    public double getMoonPhase() {
        return moonPhase;
    }

    /**
     * 
     * @param moonPhase
     *     The moonPhase
     */
    public void setMoonPhase(double moonPhase) {
        this.moonPhase = moonPhase;
    }

    /**
     * 
     * @return
     *     The precipIntensity
     */
    public double getPrecipIntensity() {
        return precipIntensity;
    }

    /**
     * 
     * @param precipIntensity
     *     The precipIntensity
     */
    public void setPrecipIntensity(double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    /**
     * 
     * @return
     *     The precipIntensityMax
     */
    public Double getPrecipIntensityMax() {
        return precipIntensityMax;
    }

    /**
     * 
     * @param precipIntensityMax
     *     The precipIntensityMax
     */
    public void setPrecipIntensityMax(Double precipIntensityMax) {
        this.precipIntensityMax = precipIntensityMax;
    }

    /**
     * 
     * @return
     *     The precipProbability
     */
    public Double getPrecipProbability() {
        return precipProbability;
    }

    /**
     * 
     * @param precipProbability
     *     The precipProbability
     */
    public void setPrecipProbability(Double precipProbability) {
        this.precipProbability = precipProbability;
    }

    /**
     * 
     * @return
     *     The temperatureMin
     */
    public double getTemperatureMin() {
        return temperatureMin;
    }

    /**
     * 
     * @param temperatureMin
     *     The temperatureMin
     */
    public void setTemperatureMin(double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    /**
     * 
     * @return
     *     The temperatureMinTime
     */
    public int getTemperatureMinTime() {
        return temperatureMinTime;
    }

    /**
     * 
     * @param temperatureMinTime
     *     The temperatureMinTime
     */
    public void setTemperatureMinTime(int temperatureMinTime) {
        this.temperatureMinTime = temperatureMinTime;
    }

    /**
     * 
     * @return
     *     The temperatureMax
     */
    public double getTemperatureMax() {
        return temperatureMax;
    }

    /**
     * 
     * @param temperatureMax
     *     The temperatureMax
     */
    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    /**
     * 
     * @return
     *     The temperatureMaxTime
     */
    public int getTemperatureMaxTime() {
        return temperatureMaxTime;
    }

    /**
     * 
     * @param temperatureMaxTime
     *     The temperatureMaxTime
     */
    public void setTemperatureMaxTime(int temperatureMaxTime) {
        this.temperatureMaxTime = temperatureMaxTime;
    }

    /**
     * 
     * @return
     *     The apparentTemperatureMin
     */
    public double getApparentTemperatureMin() {
        return apparentTemperatureMin;
    }

    /**
     * 
     * @param apparentTemperatureMin
     *     The apparentTemperatureMin
     */
    public void setApparentTemperatureMin(double apparentTemperatureMin) {
        this.apparentTemperatureMin = apparentTemperatureMin;
    }

    /**
     * 
     * @return
     *     The apparentTemperatureMinTime
     */
    public int getApparentTemperatureMinTime() {
        return apparentTemperatureMinTime;
    }

    /**
     * 
     * @param apparentTemperatureMinTime
     *     The apparentTemperatureMinTime
     */
    public void setApparentTemperatureMinTime(int apparentTemperatureMinTime) {
        this.apparentTemperatureMinTime = apparentTemperatureMinTime;
    }

    /**
     * 
     * @return
     *     The apparentTemperatureMax
     */
    public double getApparentTemperatureMax() {
        return apparentTemperatureMax;
    }

    /**
     * 
     * @param apparentTemperatureMax
     *     The apparentTemperatureMax
     */
    public void setApparentTemperatureMax(double apparentTemperatureMax) {
        this.apparentTemperatureMax = apparentTemperatureMax;
    }

    /**
     * 
     * @return
     *     The apparentTemperatureMaxTime
     */
    public int getApparentTemperatureMaxTime() {
        return apparentTemperatureMaxTime;
    }

    /**
     * 
     * @param apparentTemperatureMaxTime
     *     The apparentTemperatureMaxTime
     */
    public void setApparentTemperatureMaxTime(int apparentTemperatureMaxTime) {
        this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
    }

    /**
     * 
     * @return
     *     The dewPoint
     */
    public double getDewPoint() {
        return dewPoint;
    }

    /**
     * 
     * @param dewPoint
     *     The dewPoint
     */
    public void setDewPoint(double dewPoint) {
        this.dewPoint = dewPoint;
    }

    /**
     * 
     * @return
     *     The humidity
     */
    public double getHumidity() {
        return humidity;
    }

    /**
     * 
     * @param humidity
     *     The humidity
     */
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    /**
     * 
     * @return
     *     The windSpeed
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * 
     * @param windSpeed
     *     The windSpeed
     */
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * 
     * @return
     *     The windBearing
     */
    public int getWindBearing() {
        return windBearing;
    }

    /**
     * 
     * @param windBearing
     *     The windBearing
     */
    public void setWindBearing(int windBearing) {
        this.windBearing = windBearing;
    }

    /**
     * 
     * @return
     *     The visibility
     */
    public Double getVisibility() {
        return visibility;
    }

    /**
     * 
     * @param visibility
     *     The visibility
     */
    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

    /**
     * 
     * @return
     *     The cloudCover
     */
    public Double getCloudCover() {
        return cloudCover;
    }

    /**
     * 
     * @param cloudCover
     *     The cloudCover
     */
    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
    }

    /**
     * 
     * @return
     *     The pressure
     */
    public double getPressure() {
        return pressure;
    }

    /**
     * 
     * @param pressure
     *     The pressure
     */
    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    /**
     * 
     * @return
     *     The ozone
     */
    public double getOzone() {
        return ozone;
    }

    /**
     * 
     * @param ozone
     *     The ozone
     */
    public void setOzone(double ozone) {
        this.ozone = ozone;
    }

    /**
     * 
     * @return
     *     The precipIntensityMaxTime
     */
    public int getPrecipIntensityMaxTime() {
        return precipIntensityMaxTime;
    }

    /**
     * 
     * @param precipIntensityMaxTime
     *     The precipIntensityMaxTime
     */
    public void setPrecipIntensityMaxTime(int precipIntensityMaxTime) {
        this.precipIntensityMaxTime = precipIntensityMaxTime;
    }

    /**
     * 
     * @return
     *     The precipType
     */
    public String getPrecipType() {
        return precipType;
    }

    /**
     * 
     * @param precipType
     *     The precipType
     */
    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(time);
        parcel.writeString(summary);
        parcel.writeString(icon);
        parcel.writeInt(sunriseTime);
        parcel.writeInt(sunsetTime);
        parcel.writeDouble(moonPhase);
        parcel.writeDouble(precipIntensity);
        parcel.writeDouble(temperatureMin);
        parcel.writeInt(temperatureMinTime);
        parcel.writeDouble(temperatureMax);
        parcel.writeInt(temperatureMaxTime);
        parcel.writeDouble(apparentTemperatureMin);
        parcel.writeInt(apparentTemperatureMinTime);
        parcel.writeDouble(apparentTemperatureMax);
        parcel.writeInt(apparentTemperatureMaxTime);
        parcel.writeDouble(dewPoint);
        parcel.writeDouble(humidity);
        parcel.writeDouble(windSpeed);
        parcel.writeInt(windBearing);
        parcel.writeDouble(pressure);
        parcel.writeDouble(ozone);
        parcel.writeInt(precipIntensityMaxTime);
        parcel.writeString(precipType);
    }
}
