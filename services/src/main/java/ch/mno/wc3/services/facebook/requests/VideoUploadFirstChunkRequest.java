package ch.mno.wc3.services.facebook.requests;
// https://developers.facebook.com/docs/video-api/guides/publishing/
// curl -X POST \
//  "https://graph-video.facebook.com/v15.0/1755847768034402/videos"  \
//  -F "upload_phase=transfer" \
//  -F "upload_session_id=2918040901584241" \
//  -F "access_token=EAADI..." \
//  -F "start_offset=0" \
//  -F "video_file_chunk=@/Users/...xaa"
//
// {
//  "start_offset":"10485760",  //Value for second chunk
//  "end_offset":"15728640"
//}
public class VideoUploadFirstChunkRequest {


}
