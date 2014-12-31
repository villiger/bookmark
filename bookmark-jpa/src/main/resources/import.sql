-- This script is being executed after Hibernate's entity manager gets booted.
-- IMPORTANT: To stop this behaviour edit the value of "hibernate.hbm2ddl.auto"
-- in the "persistence.xml" to "update".

INSERT INTO `user` (`email`, `admin`, `password`) VALUES ('admin@ffhs.ch', 1, SHA1('1234'));

INSERT INTO `link` (`title`, `url`) VALUES ('Google', 'www.google.com');
INSERT INTO `link` (`title`, `url`) VALUES ('Heise Online', 'www.heise.de');
INSERT INTO `link` (`title`, `url`) VALUES ('Spiegel Online', 'www.spiegel.de');
INSERT INTO `link` (`title`, `url`) VALUES ('NZZ', 'www.nzz.ch');
INSERT INTO `link` (`title`, `url`) VALUES ('Tagesanzeiger', 'www.tagi.ch');
INSERT INTO `link` (`title`, `url`) VALUES ('Hacker News', 'news.ycombinator.com');
INSERT INTO `link` (`title`, `url`) VALUES ('Github', 'www.github.com');
INSERT INTO `link` (`title`, `url`) VALUES ('xkcd', 'www.xkcd.com');
INSERT INTO `link` (`title`, `url`) VALUES ('GDS.FM', 'www.gds.fm');
