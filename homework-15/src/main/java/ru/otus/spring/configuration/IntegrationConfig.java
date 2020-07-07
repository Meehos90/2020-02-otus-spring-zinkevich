package ru.otus.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring.model.CarBooklet;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class IntegrationConfig {

    public static final String BOOKLET_CHANNEL = "bookletChannel";

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).get();
    }

    @Bean
    public QueueChannel ordersChannel() {
        return MessageChannels.queue(50).get();
    }

    @Bean
    public QueueChannel bookletChannel() {
        return MessageChannels.queue(50).get();
    }

    @Bean
    public QueueChannel colorsChannel() {
        return MessageChannels.queue(50).get();
    }

    @Bean
    public QueueChannel bodyTypesChannel() {
        return MessageChannels.queue(50).get();
    }

    @Bean
    public QueueChannel complectationsChannel() {
        return MessageChannels.queue(50).get();
    }

    @Bean
    public QueueChannel readyBookletChannel() {
        return MessageChannels.queue(50).get();
    }

    @Bean
    public IntegrationFlow bookletFlow() {
        return IntegrationFlows.from(BOOKLET_CHANNEL)
                .routeToRecipients(route -> route
                        .<CarBooklet> recipient("colorsChannel",
                                carBooklet -> carBooklet.getColors().isEmpty())
                        .<CarBooklet> recipient("bodyTypesChannel",
                                carBooklet -> carBooklet.getBodyTypes().isEmpty())
                        .<CarBooklet> recipient("complectationsChannel",
                                carBooklet -> carBooklet.getComplectations().isEmpty())
                        .defaultOutputChannel("readyBookletChannel"))
                .get();
    }

    @Bean
    public IntegrationFlow orderFlow() {
        return IntegrationFlows.from("ordersChannel")
                .log("Init order>")
                .handle("carBookletService", "initCarBooklet")
                .channel(BOOKLET_CHANNEL)
                .get();
    }

    @Bean
    public IntegrationFlow colorFlow() {
        return IntegrationFlows.from("colorsChannel")
                .split()
                .log("Init colors>")
                .handle("colorService", "initColor")
                .channel(BOOKLET_CHANNEL)
                .get();
    }

    @Bean
    public IntegrationFlow bodyTypeFlow() {
        return IntegrationFlows.from("bodyTypesChannel")
                .split()
                .log("Init body types>")
                .handle("bodyTypeService", "initBodyType")
                .channel(BOOKLET_CHANNEL)
                .get();
    }

    @Bean
    public IntegrationFlow complectationTypeFlow() {
        return IntegrationFlows.from("complectationsChannel")
                .split()
                .log("Init complectations>")
                .handle("complectationService", "initComplectation")
                .channel(BOOKLET_CHANNEL)
                .get();
    }

    @Bean
    public IntegrationFlow readyBooklet() {
        return IntegrationFlows.from("readyBookletChannel")
                .log("Booklet is ready>")
                .get();
    }

}
