package testes;

import java.text.ParseException;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzApp {

	public static void main(String[] args) throws Exception {
		SchedulerFactory shedFact = new StdSchedulerFactory();
		try {
			Scheduler scheduler = shedFact.getScheduler();
			scheduler.start();
			JobDetail job = JobBuilder.newJob(ValidadorJob.class)
					.withIdentity("BuscaArduinoJob", "grupo01").build();
			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("BuscaArduinoTRIGGER", "grupo01")
					.withSchedule(
							CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
					.build();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}