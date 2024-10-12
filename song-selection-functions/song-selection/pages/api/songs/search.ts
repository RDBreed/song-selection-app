import type {NextApiRequest, NextApiResponse} from "next";
import {getXataClient} from "@/src/xata";
import {getXataBranch} from "@/lib/constants";

// const songs = [
//   {number: 1, title: "Amazing Grace", bundle: "Hymns"},
//   {number: 2, title: "Blessed Assurance", bundle: "Hymns"},
//   // Add more song objects here
// ];

export default async function handler(
  req: NextApiRequest,
  res: NextApiResponse,
) {
  const {q} = req.query;

  if (!q) {
    return res.status(400).json({error: "Zoekterm is verplicht."});
  }

  console.log(getXataBranch())
  const xataClient = getXataClient();
  const results = await xataClient.db.opwekking.search(q.toString(), {target: ['Number', 'Title']})

  // const results = songs.filter((song) =>
  //   song.title.toLowerCase().includes(q.toString().toLowerCase())
  // );

  if (results.totalCount === 0) {
    return res.status(200).json([]);
  }

  return res.status(200).json(results.records);
}
