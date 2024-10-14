import type {NextApiRequest, NextApiResponse} from "next";
import {getXataClient} from "@/src/xata";
import {getXataBranch} from "@/lib/constants";

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

  if (results.totalCount === 0) {
    return res.status(200).json([]);
  }

  return res.status(200).json(results.records.map(s => ({title: `${s.Number} - ${s.Title}`, number: s.Number})));
}
