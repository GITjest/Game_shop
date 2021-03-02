DROP DATABASE IF EXISTS game_shop;
CREATE DATABASE game_shop DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci; 
USE game_shop;

CREATE TABLE roles (
	role_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`role` VARCHAR(25) NOT NULL
    );
    
CREATE TABLE users (
	user_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_name VARCHAR(25) NOT NULL,
    `password` VARCHAR(200) NOT NULL,
    registration_date DATETIME NOT NULL,
    email VARCHAR(25) NOT NULL,
    role_id INT(11) NOT NULL,
    foreign key (role_id) references roles(role_id) ON DELETE CASCADE
    );
    
CREATE TABLE requirement_types (
	requirement_type_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(100) NOT NULL
    );
    
CREATE TABLE categories (
	category_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`title` VARCHAR(100) NOT NULL
    );
    
CREATE TABLE games (
	game_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(100) NOT NULL,
    `description` VARCHAR(10000) NOT NULL,
    release_date DATE NOT NULL,
    price FLOAT NOT NULL
    );
    
CREATE TABLE games_categories (
	game_id int(11) NOT NULL,
    category_id int(11) NOT NULL,
    foreign key (game_id) references games(game_id) ON DELETE CASCADE,
    foreign key (category_id) references categories(category_id) ON DELETE CASCADE
	);

CREATE TABLE requirements (
	requirement_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    game_id INT(11) NOT NULL,
    requirement_type_id INT(11) NOT NULL,
    foreign key (game_id) references games(game_id) ON DELETE CASCADE,
	foreign key (requirement_type_id) references requirement_types(requirement_type_id)
    );
    
CREATE TABLE reviews (
	review_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `description` VARCHAR(2000) NOT NULL,
    user_id INT(11) NOT NULL,
    game_id INT(11) NOT NULL,
    foreign key (user_id) references users(user_id) ON DELETE CASCADE,
    foreign key (game_id) references games(game_id) ON DELETE CASCADE
    );
    
CREATE TABLE discounts (
	discount_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    game_id INT(11) NOT NULL,
    `value` INT(11) NOT NULL,
	start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    foreign key (game_id) references games(game_id) ON DELETE CASCADE
    );
    
CREATE TABLE orders (
	order_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	end_date DATETIME,
    user_id INT(11) NOT NULL,
    game_id INT(11) NOT NULL,
    foreign key (user_id) references users(user_id) ON DELETE CASCADE,
    foreign key (game_id) references games(game_id) ON DELETE CASCADE
    );
    
CREATE TABLE photos (
	photo_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	link VARCHAR(300) NOT NULL,
    game_id INT(11) NOT NULL,
    foreign key (game_id) references games(game_id) ON DELETE CASCADE
    );
    
CREATE TABLE covers (
	cover_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	link VARCHAR(300) NOT NULL,
    game_id INT(11) NOT NULL,
    foreign key (game_id) references games(game_id) ON DELETE CASCADE
    );
  
insert into roles values (1, "ADMIN");
insert into roles values (2, "USER");
insert into roles values (3, "MODERATOR");

insert into users values (1, "Admin", "1234", "2019-11-11 10:10:10", "admin@gmail.com", 1);
insert into users values (2, "Moderator", "1234", "2019-11-11 10:10:10", "moderator@gmail.com", 3);
insert into users values (3, "Jan", "1234", "2019-11-22 10:11:10", "jan@gmail.com", 2);
insert into users values (4, "Mirek", "1234", "2019-11-23 18:12:10", "mirek@gmail.com", 2);
insert into users values (5, "Anna", "1234", "2019-12-31 11:11:10", "anna@gmail.com", 2);
insert into users values (6, "Marek", "1234", "2019-12-11 12:13:10", "marek@gmail.com", 2);

insert into requirement_types values (1, "System operacyjny");
insert into requirement_types values (2, "Procesor");
insert into requirement_types values (3, "Pamięć");
insert into requirement_types values (4, "Karta graficzna");
insert into requirement_types values (5, "DirectX");
insert into requirement_types values (6, "Miejsce na dysku");
insert into requirement_types values (7, "Karta dźwiękowa");

insert into categories values (1, "RPG");
insert into categories values (2, "Strategie");
insert into categories values (3, "Akcja");
insert into categories values (4, "MMO");
insert into categories values (5, "Niezależne");
insert into categories values (6, "Przygodowe");
insert into categories values (7, "Rekreacyjne");
insert into categories values (8, "Sprotowe");
insert into categories values (9, "Symulacje");
insert into categories values (10, "Wyścigowe");

insert into games values (1, "Expeditions: Viking", "Przygotuj się na historyczną przygodę! Logic Artists, twórcy Expeditions: Conquistador, z przyjemnością przedstawiają grę Expeditions: Viking.<br><br>Wielka przygoda już czeka<br/></br>Właśnie przypadł ci szczególny spadek: przywództwo niewielkiego klanu wikingów zamieszkującego wioskę na wybrzeżach Jutlandii. Zanim wyryjesz runy swego imienia w kamieniu historii, musisz zbudować potęgę swojego klanu i zgromadzić bogactwa, które zapewnią mu dobrobyt. W swojej ojczyźnie, pełnej wrogów i spisków, niewiele możesz zdziałać – musisz skierować wzrok daleko na zachód, za morze, gdzie ponoć wielka wyspa pełna skarbów czeka, by ją złupić.<br><br>Szukaj szczęścia w dalekich krainach<br><br>Twoi oddani huskarlowie podążą za tobą nawet do Walhalli, jeśli będzie trzeba, ale sama lojalność nie wystarczy, aby stworzyć dziedzictwo, o którym będą pamiętać przez tysiąc lat. Zbierz hirdę wojowników, zbuduj langskip, a potem ruszaj po złoto i chwałę za morze. Brytania czeka na ciebie w grze Expeditions: Viking firmy Logic Artists.<br><br>Najważniejsze elementy:<br><br>Stwórz własnego wojownika lub wojowniczkę! Utwórz swoją postać w naszym wyjątkowym systemie postaci, gdzie współczynniki, umiejętności i zdolności określą jej rolę.<br>Najazdy czy handel? Wikingowie byli znani nie tylko jako bezwzględni wojownicy, ale również jako zmyślni kupcy. Jak zapewnisz przyszłość swojego klanu: toporem czy trzosem?<br>Wojna i polityka: Opowiedz się po stronie różnych frakcji, takich jak Piktowie czy Anglowie.<br>Reputacja: Uważaj, jakie podejmujesz decyzje: opowieści o twoich czynach mogą sprawić, że ludzie zaczną się ciebie bać, ale też przestaną ci ufać.<br>Znajdź się na kartach historii – w pięknej i sugestywnej opowieści o wikingach.<br>", "2017-09-27", 119.99);

insert into games values (2, "Sid Meier’s Civilization® VI", "Pierwotnym twórcą tytułu jest legendarny projektant gier, Sid Meier, a jego dzieło to turowa gra strategiczna stawiająca gracza na czele imperium, które ma pod jego wodzą przetrwać próbę czasu. Zostań władcą świata, zakładając swoją cywilizację i przewodząc jej od epoki kamienia aż po informacyjną. Tocz wojny, paraj się dyplomacją, rozwijaj kulturę i stawiaj czoła największym przywódcom historii na swojej drodze ku stworzeniu najwspanialszej cywilizacji, jaką znały wieki.<br><br>Civilization VI oferuje całkiem nowe sposoby na interakcję ze światem: teraz miasta w widoczny sposób rozprzestrzeniają się po mapie, aktywny wkład w badania technologiczne i kulturowe otwiera zupełnie nowe możliwości, a konkurujący ze sobą władcy dążą do realizacji agend opartych o przypisywane im historyczne cechy, ścigając się ku jednemu z pięciu dostępnych w grze laurów zwycięstwa.", "2016-10-21", 257.90);

insert into games values (3, "A Plague Tale: Innocence", "Poznaj ponurą historię Amicii oraz jej młodszego brata Hugo podczas rozdzierającej serce podróży w najczarniejszej godzinie ludzkości. Ścigani przez żołnierzy Inkwizycji i otoczeni przez stada dzikich szczurów, Amicia i Hugo muszą nauczyć się ufać sobie wzajemnie. Próbując rozpaczliwie przeżyć w starciu z przeważającym wrogiem, walczą o odnalezienie sensu w tym brutalnym i bezlitosnym świecie.", "2019-05-14", 189.99);

insert into games values (4, "DOOM Eternal", "Piekło napadło na Ziemię. Jako Slayer pokonaj demony w wielu wymiarach, by powstrzymać zniszczenie ludzkości.
Jedynym, Czego Się Boją... Jesteś Ty.<br/>
Poczuj wyjątkową kombinację szybkości i siły w DOOM Eternal – najnowszej odsłonie agresywnej walki w widoku z pierwszej osoby.<br/></br>

Odkryj pochodzenie Slayera i jego nieustającej misji: zniszczenia Piekła.<br/>
Maksymalny Poziom Zagrożenia Slayerem<br/>
Z miotaczem ognia na ramieniu, wysuwanym z nadgarstka ostrzem, z ulepszonymi spluwami i modyfikacjami twoja szybkość, siła i wszechstronność są większe niż kiedykolwiek.<br/>
Nieświęta Trójca<br/>
Zabierz wrogom, co chcesz: zdrowie po zabójstwie chwały, pancerz po spopieleniu, a amunicję po rozcięciu demona piłą. Zostań niezwyciężonym zabójcą demonów.<br/>
Wejdź W Battlemode<br/>
To nowy tryb wieloosobowy 2 vs 1 wymyślony od podstaw przez id Software. Uzbrojony po zęby DOOM Slayer mierzy się z dwoma demonami sterowanymi przez innych graczy w intensywnym, pierwszoosobowym meczu do trzech zwycięstw.", "2020-03-20", 149.99);

insert into games_categories values (1, 1);
insert into games_categories values (1, 2);
insert into games_categories values (2, 2);
insert into games_categories values (3, 6);
insert into games_categories values (4, 3);

insert into requirements values (1, "Windows 7", 1, 1);
insert into requirements values (2, "Intel Core2 Quad Q9400 2.66 GHz", 1, 2);
insert into requirements values (3, "4 GB RAM", 1, 3);
insert into requirements values (4, "NVIDIA GeForce GTS 450", 1, 4);
insert into requirements values (5, "Wersja 11", 1, 5);
insert into requirements values (6, "12 GB", 1, 6);
insert into requirements values (7, "DirectX Compatible Sound Card", 1, 7);

insert into requirements values (8, "Windows 7", 2, 1);
insert into requirements values (9, "Intel Core2 Quad Q9400 2.66 GHz", 2, 2);
insert into requirements values (10, "4 GB RAM", 2, 3);
insert into requirements values (11, "NVIDIA GeForce GTS 450", 2, 4);
insert into requirements values (12, "Wersja 11", 2, 5);
insert into requirements values (13, "12 GB", 2, 6);
insert into requirements values (14, "DirectX Compatible Sound Card", 2, 7);

insert into requirements values (15, "Windows 7/8/10 (64 bits)", 3, 1);
insert into requirements values (16, "Intel Core i3-2120 (3.3 GHz)/AMD FX-4100 X4 (3.6 GHz)", 3, 2);
insert into requirements values (17, "8 GB RAM", 3, 3);
insert into requirements values (18, "2 GB, GeForce GTX 660/Radeon HD 7870", 3, 4);
insert into requirements values (19, "Wersja 11", 3, 5);
insert into requirements values (20, "50 GB", 3, 6);
insert into requirements values (21, "DirectX Compatible Sound Card", 3, 7);

insert into reviews values (1, "Ukończona po 65 godzinach grania, dawno żaden erpeg tak mnie nie wciągnął, z największym trudem odchodziłam od monitora. Powalający klimat, genialna muzyka, dużo trudnych moralnie wyborów. Nie obyło się niestety bez bugów, ale jak na grę tak małego studia jest naprawdę rewelacyjnie i z uwagi na oryginalność i rozmiar produkcji wiele można wybaczyć. Polecam wszystkim fanom gier pokroju Baldura, Pillarsów itp.", 3, 1);
insert into reviews values (3, "Ukończona po 65 godzinach grania, dawno żaden erpeg tak mnie nie wciągnął, z największym trudem odchodziłam od monitora. Powalający klimat, genialna muzyka, dużo trudnych moralnie wyborów. Nie obyło się niestety bez bugów, ale jak na grę tak małego studia jest naprawdę rewelacyjnie i z uwagi na oryginalność i rozmiar produkcji wiele można wybaczyć. Polecam wszystkim fanom gier pokroju Baldura, Pillarsów itp.", 3, 2);

insert into discounts values (1, 1, 60, "2021-01-20", "2021-03-27");
insert into discounts values (2, 2, 70, "2020-01-20", "2020-03-27");
insert into discounts values (3, 3, 60, "2020-01-20", "2020-03-27");

insert into orders values (1, "2020-01-10 16:00:00", 3, 1);
insert into orders values (2, "2020-01-10 16:00:00", 3, 2);
insert into orders values (3, "2020-01-10 16:00:00", 1, 3);

insert into photos values (1, "https://www.gry-online.pl/galeria_duzee/88081861.jpg", 1);
insert into photos values (2, "https://www.gry-online.pl/galeria_duzee/88080613.jpg", 1);
insert into photos values (3, "https://www.gry-online.pl/galeria_duzee/528347781.jpg", 3);
insert into photos values (4, "https://www.gry-online.pl/galeria_duzee/603816328.jpg", 4);
    
insert into covers values(1, "https://cdn.gracza.pl/galeria/gry13/90312005.jpg", 1);
insert into covers values(2, "https://cdn.gracza.pl/galeria/gry13/grupy/432428343.jpg", 2);
insert into covers values(3,"https://cdn.gracza.pl/galeria/gry13/grupy/19346.jpg", 3);
insert into covers values(4, "https://cdn.gracza.pl/galeria/gry13/grupy/6103953.jpg", 4);