package mk.ukim.finki.wp.emtlab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
}

//avtomatsko sledenje na koga e kreiran zapis/izmenet/koj go napravil toa
