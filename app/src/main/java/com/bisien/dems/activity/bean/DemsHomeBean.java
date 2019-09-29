package com.bisien.dems.activity.bean;

public class DemsHomeBean {

    /**
     * code : 0
     * message :
     * data : {"alarmGrade1":0,"alarmGrade2":380,"alarmGrade3":0,"alarmPercent":9.1,"ac":{"category":1,"param1":0,"param2":0,"param3":0,"count":0},"ups":{"category":7,"param1":0,"param2":0,"param3":0,"count":1},"sts":{"category":2,"param1":0,"param2":0,"param3":0,"count":1},"dc":{"category":3,"param1":0,"param2":0,"param3":0,"count":1},"ths":{"category":4,"param1":0,"param2":0,"param3":0,"count":6},"fs":{"category":5,"param1":0,"param2":0,"param3":0,"count":0},"ab":{"category":6,"param1":0,"param2":0,"param3":0,"count":1},"env":{"category":0,"param1":0,"param2":0,"param3":0,"count":0},"other":{"category":9,"param1":0,"param2":0,"param3":0,"count":0},"equipCount":11,"equipAlarm":1,"abnormalComm":null,"unconfirmed":380,"acAlarm":0,"envAlarm":0,"upsAlarm":0,"stsAlarm":0,"dcAlarm":0,"abAlarm":0,"thsAlarm":0,"fsAlarm":0,"otherAlarm":0,"acUnconfirmed":0,"envUnconfirmed":380,"upsUnconfirmed":0,"stsUnconfirmed":380,"dcUnconfirmed":0,"abUnconfirmed":0,"thsUnconfirmed":0,"fsUnconfirmed":0,"otherUnconfirmed":0}
     */

    private String code;
    private String message;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * alarmGrade1 : 0
         * alarmGrade2 : 380
         * alarmGrade3 : 0
         * alarmPercent : 9.1
         * ac : {"category":1,"param1":0,"param2":0,"param3":0,"count":0}
         * ups : {"category":7,"param1":0,"param2":0,"param3":0,"count":1}
         * sts : {"category":2,"param1":0,"param2":0,"param3":0,"count":1}
         * dc : {"category":3,"param1":0,"param2":0,"param3":0,"count":1}
         * ths : {"category":4,"param1":0,"param2":0,"param3":0,"count":6}
         * fs : {"category":5,"param1":0,"param2":0,"param3":0,"count":0}
         * ab : {"category":6,"param1":0,"param2":0,"param3":0,"count":1}
         * env : {"category":0,"param1":0,"param2":0,"param3":0,"count":0}
         * other : {"category":9,"param1":0,"param2":0,"param3":0,"count":0}
         * equipCount : 11
         * equipAlarm : 1
         * abnormalComm : null
         * unconfirmed : 380
         * acAlarm : 0
         * envAlarm : 0
         * upsAlarm : 0
         * stsAlarm : 0
         * dcAlarm : 0
         * abAlarm : 0
         * thsAlarm : 0
         * fsAlarm : 0
         * otherAlarm : 0
         * acUnconfirmed : 0
         * envUnconfirmed : 380
         * upsUnconfirmed : 0
         * stsUnconfirmed : 380
         * dcUnconfirmed : 0
         * abUnconfirmed : 0
         * thsUnconfirmed : 0
         * fsUnconfirmed : 0
         * otherUnconfirmed : 0
         */

        private int alarmGrade1;
        private int alarmGrade2;
        private int alarmGrade3;
        private double alarmPercent;
        private AcBean ac;
        private UpsBean ups;
        private StsBean sts;
        private DcBean dc;
        private ThsBean ths;
        private FsBean fs;
        private AbBean ab;
        private EnvBean env;
        private OtherBean other;
        private int equipCount;
        private int equipAlarm;
        private Object abnormalComm;
        private int unconfirmed;
        private int acAlarm;
        private int envAlarm;
        private int upsAlarm;
        private int stsAlarm;
        private int dcAlarm;
        private int abAlarm;
        private int thsAlarm;
        private int fsAlarm;
        private int otherAlarm;
        private int acUnconfirmed;
        private int envUnconfirmed;
        private int upsUnconfirmed;
        private int stsUnconfirmed;
        private int dcUnconfirmed;
        private int abUnconfirmed;
        private int thsUnconfirmed;
        private int fsUnconfirmed;
        private int otherUnconfirmed;

        public int getAlarmGrade1() {
            return alarmGrade1;
        }

        public void setAlarmGrade1(int alarmGrade1) {
            this.alarmGrade1 = alarmGrade1;
        }

        public int getAlarmGrade2() {
            return alarmGrade2;
        }

        public void setAlarmGrade2(int alarmGrade2) {
            this.alarmGrade2 = alarmGrade2;
        }

        public int getAlarmGrade3() {
            return alarmGrade3;
        }

        public void setAlarmGrade3(int alarmGrade3) {
            this.alarmGrade3 = alarmGrade3;
        }

        public double getAlarmPercent() {
            return alarmPercent;
        }

        public void setAlarmPercent(double alarmPercent) {
            this.alarmPercent = alarmPercent;
        }

        public AcBean getAc() {
            return ac;
        }

        public void setAc(AcBean ac) {
            this.ac = ac;
        }

        public UpsBean getUps() {
            return ups;
        }

        public void setUps(UpsBean ups) {
            this.ups = ups;
        }

        public StsBean getSts() {
            return sts;
        }

        public void setSts(StsBean sts) {
            this.sts = sts;
        }

        public DcBean getDc() {
            return dc;
        }

        public void setDc(DcBean dc) {
            this.dc = dc;
        }

        public ThsBean getThs() {
            return ths;
        }

        public void setThs(ThsBean ths) {
            this.ths = ths;
        }

        public FsBean getFs() {
            return fs;
        }

        public void setFs(FsBean fs) {
            this.fs = fs;
        }

        public AbBean getAb() {
            return ab;
        }

        public void setAb(AbBean ab) {
            this.ab = ab;
        }

        public EnvBean getEnv() {
            return env;
        }

        public void setEnv(EnvBean env) {
            this.env = env;
        }

        public OtherBean getOther() {
            return other;
        }

        public void setOther(OtherBean other) {
            this.other = other;
        }

        public int getEquipCount() {
            return equipCount;
        }

        public void setEquipCount(int equipCount) {
            this.equipCount = equipCount;
        }

        public int getEquipAlarm() {
            return equipAlarm;
        }

        public void setEquipAlarm(int equipAlarm) {
            this.equipAlarm = equipAlarm;
        }

        public Object getAbnormalComm() {
            return abnormalComm;
        }

        public void setAbnormalComm(Object abnormalComm) {
            this.abnormalComm = abnormalComm;
        }

        public int getUnconfirmed() {
            return unconfirmed;
        }

        public void setUnconfirmed(int unconfirmed) {
            this.unconfirmed = unconfirmed;
        }

        public int getAcAlarm() {
            return acAlarm;
        }

        public void setAcAlarm(int acAlarm) {
            this.acAlarm = acAlarm;
        }

        public int getEnvAlarm() {
            return envAlarm;
        }

        public void setEnvAlarm(int envAlarm) {
            this.envAlarm = envAlarm;
        }

        public int getUpsAlarm() {
            return upsAlarm;
        }

        public void setUpsAlarm(int upsAlarm) {
            this.upsAlarm = upsAlarm;
        }

        public int getStsAlarm() {
            return stsAlarm;
        }

        public void setStsAlarm(int stsAlarm) {
            this.stsAlarm = stsAlarm;
        }

        public int getDcAlarm() {
            return dcAlarm;
        }

        public void setDcAlarm(int dcAlarm) {
            this.dcAlarm = dcAlarm;
        }

        public int getAbAlarm() {
            return abAlarm;
        }

        public void setAbAlarm(int abAlarm) {
            this.abAlarm = abAlarm;
        }

        public int getThsAlarm() {
            return thsAlarm;
        }

        public void setThsAlarm(int thsAlarm) {
            this.thsAlarm = thsAlarm;
        }

        public int getFsAlarm() {
            return fsAlarm;
        }

        public void setFsAlarm(int fsAlarm) {
            this.fsAlarm = fsAlarm;
        }

        public int getOtherAlarm() {
            return otherAlarm;
        }

        public void setOtherAlarm(int otherAlarm) {
            this.otherAlarm = otherAlarm;
        }

        public int getAcUnconfirmed() {
            return acUnconfirmed;
        }

        public void setAcUnconfirmed(int acUnconfirmed) {
            this.acUnconfirmed = acUnconfirmed;
        }

        public int getEnvUnconfirmed() {
            return envUnconfirmed;
        }

        public void setEnvUnconfirmed(int envUnconfirmed) {
            this.envUnconfirmed = envUnconfirmed;
        }

        public int getUpsUnconfirmed() {
            return upsUnconfirmed;
        }

        public void setUpsUnconfirmed(int upsUnconfirmed) {
            this.upsUnconfirmed = upsUnconfirmed;
        }

        public int getStsUnconfirmed() {
            return stsUnconfirmed;
        }

        public void setStsUnconfirmed(int stsUnconfirmed) {
            this.stsUnconfirmed = stsUnconfirmed;
        }

        public int getDcUnconfirmed() {
            return dcUnconfirmed;
        }

        public void setDcUnconfirmed(int dcUnconfirmed) {
            this.dcUnconfirmed = dcUnconfirmed;
        }

        public int getAbUnconfirmed() {
            return abUnconfirmed;
        }

        public void setAbUnconfirmed(int abUnconfirmed) {
            this.abUnconfirmed = abUnconfirmed;
        }

        public int getThsUnconfirmed() {
            return thsUnconfirmed;
        }

        public void setThsUnconfirmed(int thsUnconfirmed) {
            this.thsUnconfirmed = thsUnconfirmed;
        }

        public int getFsUnconfirmed() {
            return fsUnconfirmed;
        }

        public void setFsUnconfirmed(int fsUnconfirmed) {
            this.fsUnconfirmed = fsUnconfirmed;
        }

        public int getOtherUnconfirmed() {
            return otherUnconfirmed;
        }

        public void setOtherUnconfirmed(int otherUnconfirmed) {
            this.otherUnconfirmed = otherUnconfirmed;
        }

        public static class AcBean {
            /**
             * category : 1
             * param1 : 0.0
             * param2 : 0.0
             * param3 : 0.0
             * count : 0
             */

            private int category;
            private double param1;
            private double param2;
            private double param3;
            private int count;

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public double getParam1() {
                return param1;
            }

            public void setParam1(double param1) {
                this.param1 = param1;
            }

            public double getParam2() {
                return param2;
            }

            public void setParam2(double param2) {
                this.param2 = param2;
            }

            public double getParam3() {
                return param3;
            }

            public void setParam3(double param3) {
                this.param3 = param3;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }

        public static class UpsBean {
            /**
             * category : 7
             * param1 : 0.0
             * param2 : 0.0
             * param3 : 0.0
             * count : 1
             */

            private int category;
            private double param1;
            private double param2;
            private double param3;
            private int count;

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public double getParam1() {
                return param1;
            }

            public void setParam1(double param1) {
                this.param1 = param1;
            }

            public double getParam2() {
                return param2;
            }

            public void setParam2(double param2) {
                this.param2 = param2;
            }

            public double getParam3() {
                return param3;
            }

            public void setParam3(double param3) {
                this.param3 = param3;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }

        public static class StsBean {
            /**
             * category : 2
             * param1 : 0.0
             * param2 : 0.0
             * param3 : 0.0
             * count : 1
             */

            private int category;
            private double param1;
            private double param2;
            private double param3;
            private int count;

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public double getParam1() {
                return param1;
            }

            public void setParam1(double param1) {
                this.param1 = param1;
            }

            public double getParam2() {
                return param2;
            }

            public void setParam2(double param2) {
                this.param2 = param2;
            }

            public double getParam3() {
                return param3;
            }

            public void setParam3(double param3) {
                this.param3 = param3;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }

        public static class DcBean {
            /**
             * category : 3
             * param1 : 0.0
             * param2 : 0.0
             * param3 : 0.0
             * count : 1
             */

            private int category;
            private double param1;
            private double param2;
            private double param3;
            private int count;

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public double getParam1() {
                return param1;
            }

            public void setParam1(double param1) {
                this.param1 = param1;
            }

            public double getParam2() {
                return param2;
            }

            public void setParam2(double param2) {
                this.param2 = param2;
            }

            public double getParam3() {
                return param3;
            }

            public void setParam3(double param3) {
                this.param3 = param3;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }

        public static class ThsBean {
            /**
             * category : 4
             * param1 : 0.0
             * param2 : 0.0
             * param3 : 0.0
             * count : 6
             */

            private int category;
            private double param1;
            private double param2;
            private double param3;
            private int count;

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public double getParam1() {
                return param1;
            }

            public void setParam1(double param1) {
                this.param1 = param1;
            }

            public double getParam2() {
                return param2;
            }

            public void setParam2(double param2) {
                this.param2 = param2;
            }

            public double getParam3() {
                return param3;
            }

            public void setParam3(double param3) {
                this.param3 = param3;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }

        public static class FsBean {
            /**
             * category : 5
             * param1 : 0.0
             * param2 : 0.0
             * param3 : 0.0
             * count : 0
             */

            private int category;
            private double param1;
            private double param2;
            private double param3;
            private int count;

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public double getParam1() {
                return param1;
            }

            public void setParam1(double param1) {
                this.param1 = param1;
            }

            public double getParam2() {
                return param2;
            }

            public void setParam2(double param2) {
                this.param2 = param2;
            }

            public double getParam3() {
                return param3;
            }

            public void setParam3(double param3) {
                this.param3 = param3;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }

        public static class AbBean {
            /**
             * category : 6
             * param1 : 0.0
             * param2 : 0.0
             * param3 : 0.0
             * count : 1
             */

            private int category;
            private double param1;
            private double param2;
            private double param3;
            private int count;

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public double getParam1() {
                return param1;
            }

            public void setParam1(double param1) {
                this.param1 = param1;
            }

            public double getParam2() {
                return param2;
            }

            public void setParam2(double param2) {
                this.param2 = param2;
            }

            public double getParam3() {
                return param3;
            }

            public void setParam3(double param3) {
                this.param3 = param3;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }

        public static class EnvBean {
            /**
             * category : 0
             * param1 : 0.0
             * param2 : 0.0
             * param3 : 0.0
             * count : 0
             */

            private int category;
            private double param1;
            private double param2;
            private double param3;
            private int count;

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public double getParam1() {
                return param1;
            }

            public void setParam1(double param1) {
                this.param1 = param1;
            }

            public double getParam2() {
                return param2;
            }

            public void setParam2(double param2) {
                this.param2 = param2;
            }

            public double getParam3() {
                return param3;
            }

            public void setParam3(double param3) {
                this.param3 = param3;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }

        public static class OtherBean {
            /**
             * category : 9
             * param1 : 0.0
             * param2 : 0.0
             * param3 : 0.0
             * count : 0
             */

            private int category;
            private double param1;
            private double param2;
            private double param3;
            private int count;

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public double getParam1() {
                return param1;
            }

            public void setParam1(double param1) {
                this.param1 = param1;
            }

            public double getParam2() {
                return param2;
            }

            public void setParam2(double param2) {
                this.param2 = param2;
            }

            public double getParam3() {
                return param3;
            }

            public void setParam3(double param3) {
                this.param3 = param3;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }
    }
}
