package kr.ac.kopo.eda.controller;

import javax.servlet.http.HttpServletRequest;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RController {

	// R
/*	@RequestMapping("/R")
	public ModelAndView rExcercise(HttpServletRequest request) throws REngineException, REXPMismatchException{
		ModelAndView mav = new ModelAndView("eda/R");

		RConnection r = null;
		System.out.println(1);
		try {
			r = new RConnection();

			System.out.println(1);
			
			r.eval("library('RJDBC')");
			r.eval("library('dplyr')");
			r.eval("library('ggplot2')");
			r.eval("library('reshape')");
			
			System.out.println(2);
			
			r.eval("jdbc_driver <- JDBC('oracle.jdbc.OracleDriver', classPath = 'C:/Users/rn926/eclipse-webWorkspace/webWorkspace/wtpwebapps/Test2-MVC/WEB-INF/lib/ojdbc8.jar')");
			r.eval("conn <- dbConnect(jdbc_driver, 'jdbc:oracle:thin:@//192.168.11.1:1521/xe', 'jaehee', '1111')");
			r.eval("query <- \"select to_char(log_date, 'yy/mm') as month, sum(amount) as amount from dw_card_log where log_type_key != '1' and account_number = '111-111111-11111' group by to_char(log_date, 'yy/mm') order by month\"");
			r.eval("dw_account_log <- dbGetQuery(conn, query)");
			r.eval("amount <- dw_account_log %>% select(AMOUNT)");
			
			r.eval("ts_object <- ts(amount, frequency = 12, start = c(17,9))");
			r.eval("hw_object<-HoltWinters(ts_object)");
			r.eval("forecast<-predict(hw_object,  n.ahead=3,  prediction.interval=T,  level=0.95)");
			r.eval("for_values<-data.frame(time=round(time(forecast),  3),  value_forecast=as.data.frame(forecast)$fit,  dev=as.data.frame(forecast)$upr-as.data.frame(forecast)$fit)");
			r.eval("fitted_values<-data.frame(time=round(time(hw_object$fitted),  3),  value_fitted=as.data.frame(hw_object$fitted)$xhat)");
			r.eval("actual_values<-data.frame(time=round(time(hw_object$x),  3),  Actual=c(hw_object$x))");
			r.eval("graphset<-merge(actual_values,  fitted_values,  by='time',  all=TRUE)");
			r.eval("graphset<-merge(graphset,  for_values,  all=TRUE,  by='time')");
			r.eval("graphset[is.na(graphset$dev),  ]$dev<-0");
			r.eval("graphset$Fitted<-c(rep(NA,  NROW(graphset)-(NROW(for_values) + NROW(fitted_values))),  fitted_values$value_fitted,  for_values$value_forecast)");
			r.eval("graphset.melt<-melt(graphset[, c('time', 'Actual', 'Fitted')], id='time')");
			
			System.out.println(3);
			
			r.eval("p <- ggplot(graphset.melt,  aes(x=time,  y=value)) + geom_ribbon(data=graphset, aes(x=time, y=Fitted, ymin=Fitted-dev,  ymax=Fitted + dev),  alpha=.2,  fill='green') + geom_line(aes(colour=variable), size=1) + geom_vline(xintercept = max(actual_values$time), lty=2) + xlab('년도') + ylab('지출액') +  scale_colour_hue('') +scale_y_continuous(labels = scales::comma)");
			r.eval("ggsave('C:/Users/rn926/spring-workspace/Mission-Spring2/src/main/webapp/resources/assets/images/R/p.jpg')");
			
			System.out.println(4);
			
			r.close();
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		
		return mav;
	}
*/	
}
