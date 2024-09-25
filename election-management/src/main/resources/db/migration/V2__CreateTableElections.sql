CREATE TABLE elections (
    id VARCHAR(40) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
);

CREATE TABLE election_candidate (
    election_id VARCHAR(40) NOT NULL,
    candidate_id VARCHAR(40) NOT NULL,
    votes INTEGER DEFAULT 0,
    PRIMARY KEY (election_id, candidate_id)
);

-- https://mockaroo.com
INSERT INTO candidates (id, photo, given_name, family_name, email, phone, job_title) VALUES
    ('8ec1d275-d776-4c4c-8337-3b479d15d96c', 'https://robohash.org/inciduntreprehenderitex.png?size=50x50&set=set1', 'Tracy', 'Gorvette', 'tgorvette0@huffingtonpost.com', '594-682-1893', 'Associate Professor'),
    ('1f42202b-91e8-48f4-8498-c373c16d1ac7', 'https://robohash.org/etestexercitationem.png?size=50x50&set=set1', 'Kevyn', 'Rutherfoord', 'krutherfoord1@homestead.com', '809-528-8434', 'Operator'),
    ('84db7f56-6f1c-4ef6-ae3b-8c924188616a', 'https://robohash.org/etquomolestiae.png?size=50x50&set=set1', 'Tarra', 'Deener', 'tdeener2@unicef.org', '989-754-9279', 'VP Accounting'),
    ('17670422-fe9d-45d4-8404-c54a08cb362f', 'https://robohash.org/nonanimiqui.png?size=50x50&set=set1', 'Edna', 'Treeby', 'etreeby3@vistaprint.com', '345-752-1329', 'Environmental Tech'),
    ('9d4005da-1666-466e-8652-2ed83f96113d', 'https://robohash.org/temporibusvelnisi.png?size=50x50&set=set1', 'Hanna', 'Gyngyll', 'hgyngyll4@eventbrite.com', '587-351-1147', 'Senior Quality Engineer'),
    ('ce53ef02-8b2e-4415-bd38-f8645c2373ea', 'https://robohash.org/iureeaquisquam.png?size=50x50&set=set1', 'Dimitri', 'Derycot', 'dderycot5@cafepress.com', '930-667-5586', 'Technical Writer'),
    ('aba13d00-f56b-46a7-aeb3-99f63c452fee', 'https://robohash.org/accusamussintdolores.png?size=50x50&set=set1', 'Mikey', 'Odd', 'modd6@yandex.ru', '654-434-0923', 'Cost Accountant'),
    ('68ccee57-28ea-48b6-b0cd-86b4d16612b3', 'https://robohash.org/nihilfugaomnis.png?size=50x50&set=set1', 'Kahlil', 'Mallord', 'kmallord7@deliciousdays.com', '374-597-0257', 'Sales Associate'),
    ('f85f7f2b-2781-4966-b53f-2757d50cf5f1', 'https://robohash.org/inesttotam.png?size=50x50&set=set1', 'Velma', 'Unworth', 'vunworth8@stumbleupon.com', '875-406-5682', 'Nuclear Power Engineer'),
    ('91ca1d0d-8137-4023-876e-c9c3134abb3b', 'https://robohash.org/asperioresarchitectovoluptatum.png?size=50x50&set=set1', 'Fredericka', 'Dulling', 'fdulling9@zimbio.com', '662-547-7227', 'Tax Accountant');
