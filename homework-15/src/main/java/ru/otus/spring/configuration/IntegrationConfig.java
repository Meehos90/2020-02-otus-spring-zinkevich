package ru.otus.spring.configuration;

import lombok.RequiredArgsConstructor;
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
import ru.otus.spring.service.BodyTypeService;
import ru.otus.spring.service.CarBookletService;
import ru.otus.spring.service.ColorService;
import ru.otus.spring.service.ComplectationService;


@Configuration
@EnableIntegration
@IntegrationComponentScan
@RequiredArgsConstructor
public class IntegrationConfig {
    private final CarBookletService carBookletService;
    private final ColorService colorService;
    private final BodyTypeService bodyTypeService;
    private final ComplectationService complectationService;

    public static final String BOOKLET_CHANNEL = "bookletChannel";
    public static final String COLORS_CHANNEL = "colorschannel";
    public static final String BODY_TYPES_CHANNEL = "bodyTypesChannel";
    public static final String COMPLECTATIONS_CHANNEL = "complectationsChannel";
    public static final String READY_BOOKLET_CHANNEL = "readyBookletChannel";
    public static final String ORDERS_CHANNEL = "ordersChannel";

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
                        .<CarBooklet> recipient(COLORS_CHANNEL,
                                carBooklet -> carBooklet.getColors().isEmpty())
                        .<CarBooklet> recipient(BODY_TYPES_CHANNEL,
                                carBooklet -> carBooklet.getBodyTypes().isEmpty())
                        .<CarBooklet> recipient(COMPLECTATIONS_CHANNEL,
                                carBooklet -> carBooklet.getComplectations().isEmpty())
                        .defaultOutputChannel(READY_BOOKLET_CHANNEL))
                .get();
    }

    @Bean
    public IntegrationFlow orderFlow() {
        return IntegrationFlows.from(ORDERS_CHANNEL)
                .log("Init order>")
                .handle(carBookletService, "initCarBooklet")
                .channel(BOOKLET_CHANNEL)
                .get();
    }

    @Bean
    public IntegrationFlow colorFlow() {
        return IntegrationFlows.from(COLORS_CHANNEL)
                .split()
                .log("Init colors>")
                .handle(colorService, "initColor")
                .channel(BOOKLET_CHANNEL)
                .get();
    }

    @Bean
    public IntegrationFlow bodyTypeFlow() {
        return IntegrationFlows.from(BODY_TYPES_CHANNEL)
                .split()
                .log("Init body types>")
                .handle(bodyTypeService, "initBodyType")
                .channel(BOOKLET_CHANNEL)
                .get();
    }

    @Bean
    public IntegrationFlow complectationTypeFlow() {
        return IntegrationFlows.from(COMPLECTATIONS_CHANNEL)
                .split()
                .log("Init complectations>")
                .handle(complectationService, "initComplectation")
                .channel(BOOKLET_CHANNEL)
                .get();
    }

    @Bean
    public IntegrationFlow readyBooklet() {
        return IntegrationFlows.from(READY_BOOKLET_CHANNEL)
                .log("Booklet is ready>")
                .get();
    }

}
