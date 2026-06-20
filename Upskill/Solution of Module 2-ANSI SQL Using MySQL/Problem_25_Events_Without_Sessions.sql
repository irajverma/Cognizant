-- 25. Events Without Sessions
-- List all events that currently have no sessions scheduled under them.

SELECT event_id, title
FROM Events
WHERE event_id NOT IN (SELECT DISTINCT event_id FROM Sessions);