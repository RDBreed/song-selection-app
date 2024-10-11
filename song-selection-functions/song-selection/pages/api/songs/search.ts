import type {NextApiRequest, NextApiResponse} from "next";

const songs = [
  {number: 1, title: "Amazing Grace", bundle: "Hymns"},
  {number: 2, title: "Blessed Assurance", bundle: "Hymns"},
  // Add more song objects here
];

export default function handler(
  req: NextApiRequest,
  res: NextApiResponse,
) {
  const {q} = req.query;

  if (!q) {
    return res.status(400).json({error: "Zoekterm is verplicht."});
  }

  const results = songs.filter((song) =>
    song.title.toLowerCase().includes(q.toString().toLowerCase())
  );

  if (results.length === 0) {
    return res.status(200).json([]);
  }

  return res.status(200).json(results);
}
