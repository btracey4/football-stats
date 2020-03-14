create table NFL_Season_Records (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	season INT NOT NULL,
	home_team VARCHAR(50) NOT NULL,
	guest_team VARCHAR(50) NOT NULL,
	home_score INT NOT NULL,
	guest_score INT NOT NULL,
	week INT NOT NULL,
	game_date DATE NOT NULL
);