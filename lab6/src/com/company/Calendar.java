package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calendar {
    private List<Year> yearList;

    public Calendar(int countOfYears, int startYear, Year.Month.Day.DayOfWeek dayOfWeek) {
        yearList = new ArrayList<>();
        for (int i = 0; i < countOfYears; i++) {
            Year year = new Year(startYear++, dayOfWeek);
            yearList.add(year);
        }
    }

    @Override
    public String toString() {
        return "Calendar:" + '\n' + yearList;
    }

    public static class Year {
        Month[] months;
        int numberOfTheYear;

        public Year(int numberOfTheYear, Month.Day.DayOfWeek dayOfWeek) {
            this.numberOfTheYear = numberOfTheYear;
            months = new Month[12];
            Month.NameOFMonth nameOFMonth = Month.NameOFMonth.JANUARY;
            for (int i = 0; i < 12; i++) {
                months[i] = new Month(nameOFMonth, dayOfWeek);
                nameOFMonth = nameOFMonth.nextMonth();
            }
        }

        @Override
        public String toString() {
            return '\n' + "Year " + numberOfTheYear + '\n' + Arrays.toString(months);
        }

        public static class Month {
            private NameOFMonth nameOFMonth;
            private Day[] days;
            private int countOfDaysInAMonth;

            public Month(NameOFMonth nameOFMonth, Day.DayOfWeek firstDayOfMonth) {
                this.nameOFMonth = nameOFMonth;
                switch (nameOFMonth) {
                    case JANUARY:
                    case MARCH:
                    case MAY:
                    case JULY:
                    case AUGUST:
                    case OCTOBER:
                    case DECEMBER: {
                        this.countOfDaysInAMonth = 31;
                        break;
                    }
                    case FEBRUARY: {
                        this.countOfDaysInAMonth = 28;
                        break;
                    }
                    case APRIL:
                    case JUNE:
                    case SEPTEMBER:
                    case NOVEMBER: {
                        this.countOfDaysInAMonth = 30;
                        break;
                    }
                }
                this.days = new Day[countOfDaysInAMonth];
                int number = 1;
                for (int i = 0; i < countOfDaysInAMonth; i++) {
                    days[i] = new Day(number++, firstDayOfMonth);
                    firstDayOfMonth = firstDayOfMonth.nextDay();
                    if (days[i].getDayOfWeek() == Day.DayOfWeek.SATURDAY || days[i].getDayOfWeek() == Day.DayOfWeek.SUNDAY) {
                        days[i].setWeekend(true);
                    }
                }
            }

            public enum NameOFMonth {
                JANUARY("Январь"),
                FEBRUARY("Февраль"),
                MARCH("Март"),
                APRIL("Апрель"),
                MAY("Май"),
                JUNE("Июнь"),
                JULY("Июль"),
                AUGUST("Август"),
                SEPTEMBER("Сентябрь"),
                OCTOBER("Остябрь"),
                NOVEMBER("Ноябрь"),
                DECEMBER("Декабрь");

                private String name;

                NameOFMonth(String name) {
                    this.name = name;
                }

                @Override
                public String toString() {
                    return name;
                }

                public NameOFMonth nextMonth() {
                    NameOFMonth nameOFMonth = JANUARY;
                    switch (this) {
                        case JANUARY: {
                            nameOFMonth = FEBRUARY;
                            break;
                        }
                        case FEBRUARY: {
                            nameOFMonth = MARCH;
                            break;
                        }
                        case MARCH: {
                            nameOFMonth = APRIL;
                            break;
                        }
                        case APRIL: {
                            nameOFMonth = MAY;
                            break;
                        }
                        case MAY: {
                            nameOFMonth = JUNE;
                            break;
                        }
                        case JUNE: {
                            nameOFMonth = JULY;
                            break;
                        }
                        case JULY: {
                            nameOFMonth = AUGUST;
                            break;
                        }
                        case AUGUST: {
                            nameOFMonth = SEPTEMBER;
                            break;
                        }
                        case SEPTEMBER: {
                            nameOFMonth = OCTOBER;
                            break;
                        }
                        case OCTOBER: {
                            nameOFMonth = NOVEMBER;
                            break;
                        }
                        case NOVEMBER: {
                            nameOFMonth = DECEMBER;
                            break;
                        }
                        case DECEMBER: {
                            nameOFMonth = JANUARY;
                            break;
                        }
                    }
                    return nameOFMonth;
                }
            }

            @Override
            public String toString() {
                String month = "";
                month = '\n' + nameOFMonth.toString() + '\n';
                for (Day day : days) {
                    month += day.number.toString() + " ";
                    if (day.dayOfWeek == Day.DayOfWeek.SATURDAY) {
                        month += '\n';
                    }
                }
                return month;
            }

            public static class Day {
                Integer number;
                DayOfWeek dayOfWeek;
                boolean weekend;
                boolean holiday;

                public enum DayOfWeek {
                    SUNDAY,
                    MONDAY,
                    TUESDAY,
                    WEDNESDAY,
                    THURSDAY,
                    FRIDAY,
                    SATURDAY;

                    public DayOfWeek nextDay() {
                        DayOfWeek dayOfWeek = MONDAY;
                        switch (this) {
                            case MONDAY: {
                                dayOfWeek = TUESDAY;
                                break;
                            }
                            case TUESDAY: {
                                dayOfWeek = WEDNESDAY;
                                break;
                            }
                            case WEDNESDAY: {
                                dayOfWeek = THURSDAY;
                                break;
                            }
                            case THURSDAY: {
                                dayOfWeek = FRIDAY;
                                break;
                            }
                            case FRIDAY: {
                                dayOfWeek = SATURDAY;
                                break;
                            }
                            case SATURDAY: {
                                dayOfWeek = SUNDAY;
                                break;
                            }
                            case SUNDAY: {
                                dayOfWeek = MONDAY;
                                break;
                            }
                        }
                        return dayOfWeek;
                    }
                }

                public Day(int number, DayOfWeek dayOfWeek) {
                    this.number = number;
                    this.dayOfWeek = dayOfWeek;
                }

                public void setHoliday(boolean holiday) {
                    this.holiday = holiday;
                }

                public void setWeekend(boolean weekend) {
                    this.weekend = weekend;
                }

                public int getNumber() {
                    return number;
                }

                public DayOfWeek getDayOfWeek() {
                    return dayOfWeek;
                }

                public boolean isHoliday() {
                    return holiday;
                }

                public boolean isWeekend() {
                    return weekend;
                }
            }
        }
    }
}
