package lab2.controller.util;

import com.sun.istack.internal.NotNull;
import lombok.Getter;

import java.sql.Date;
import java.util.Objects;

@Getter
public class DatePair {
    private final Date startDate;
    private final Date endDate;

    public DatePair(@NotNull Date startDate, @NotNull Date endDate) {
        if (DateUtil.coherentDates(startDate, endDate)) {
            this.startDate = startDate;
            this.endDate = endDate;
        } else {
            throw new IllegalArgumentException("The dates are not coherent:\n" +
                    "\tstart date: " + startDate + "\n" +
                    "\tend date: " + endDate);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        DatePair datePair = (DatePair) obj;

        return startDate.toString().equals(datePair.startDate.toString()) &&
                endDate.toString().equals(datePair.endDate.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
